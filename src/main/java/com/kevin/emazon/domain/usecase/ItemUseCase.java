package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.IItemServicePort;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.model.ItemCategory;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemCategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.domain.util.UtilClassDomain;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.exceptions.ItemException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class ItemUseCase implements IItemServicePort {
    private final IItemPersistentPort itemPersistentPort;
    private final ICategoryPersistentPort categoryPersistentPort;
    private final IBrandPersistentPort brandPersistentPort;
    private final IItemCategoryPersistentPort itemCategoryPersistentPort;

    public ItemUseCase(IItemPersistentPort itemPersistentPort, ICategoryPersistentPort categoryPersistentPort, IBrandPersistentPort brandPersistentPort, IItemCategoryPersistentPort iItemCategoryPersistentPort) {
        this.itemPersistentPort = itemPersistentPort;
        this.categoryPersistentPort = categoryPersistentPort;
        this.brandPersistentPort = brandPersistentPort;
        this.itemCategoryPersistentPort = iItemCategoryPersistentPort;
    }

    @Override
    public void saveItem(Item item) {
        Item preparedItem = validateAndPrepareItem(item);
        Item savedItem = itemPersistentPort.saveItem(preparedItem);
        saveItemCategory(savedItem, preparedItem.getCategories());

    }

    @Override
    public List<Item> getAllByBrandName(String brandName, String order) {

        UtilClassDomain.validateOrderingMethod(order);

        List<Item> itemsWithCategories = itemPersistentPort.getItemsByBrandName(brandName, order);
        fullCategoriesInItems(itemsWithCategories);

        return itemsWithCategories;
    }

    @Override
    public List<Item> getAllByCategoryName(String categoryName, String order) {
        UtilClassDomain.validateOrderingMethod(order);

        List<Item> itemsWithCategories = itemPersistentPort.getItemsByCategoryName(categoryName, order);
        fullCategoriesInItems(itemsWithCategories);

        return itemsWithCategories;
    }

    @Override
    public List<Item> getAllByName(String itemName, String order) {
        UtilClassDomain.validateOrderingMethod(order);

        List<Item> itemsWithCategories=itemPersistentPort.getItemsByName(itemName, order);
        fullCategoriesInItems(itemsWithCategories);

        return itemsWithCategories;
    }

    @Override
    public boolean existById(Long id) {
        return itemPersistentPort.existById(id);
    }

    private void saveItemCategory(Item item, List<Category> categories) {

        List<ItemCategory>list=categories.stream().map(category -> new ItemCategory(null, item, category)).toList();
        list.forEach(itemCategoryPersistentPort::saveItemCategory);
    }

    private Item validateAndPrepareItem(Item item) {
        item.setBrand(findBrand(item.getBrand().getName()));
        item.setCategories(findCategories(item.getCategories()));
        validateList(item.getCategories());
        return item;
    }


    private List<Category> findCategories(List<Category> categories){
        return categories.stream().map(category -> categoryPersistentPort
                .findByName(category.getName())
                .orElseThrow(() -> new CategoryException("La categoría "+ category.getName()+ " que intenta agregar a este item no existe")))
                .toList();
    }

    private Brand findBrand(String name) {
        return brandPersistentPort
                .findByName(name)
                .orElseThrow(() -> new BrandException("La marca que intenta asignar a este item no existe"));
    }
    private void validateList(List<Category> categories) {
        Set<Category> noRepeatedCategory = new HashSet<>(categories);
        if (categories.isEmpty() || categories.size()>3){
            throw new ItemException("La lista de categorías no puede estar vacía, ni puede tener más de 3 categorías");
        }
        if (noRepeatedCategory.size()<categories.size()){
            throw new ItemException("Hay categorías repetidas");
        }
    }

    private void fullCategoriesInItems(List<Item> listItems) {
        listItems.forEach(item -> item.setCategories(itemCategoryPersistentPort.findCategoriesByItemId(item.getId())));
    }
}
