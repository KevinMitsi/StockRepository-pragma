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
import com.kevin.emazon.infraestructure.exceptions.IncreaseItemStockException;
import com.kevin.emazon.infraestructure.exceptions.ItemException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ItemUseCase implements IItemServicePort {
    public static final String ITEM_NOTFOUND_EXCEPTION_MESSAGE = "ItemNotfoundException";
    public static final String INCREASE_ITEM_STOCK_EXCEPTION_MESSAGE = "El valor que está intentando agregar  al item no es valido";
    public static final String BRAND_DONT_EXIST_MESSAGE = "La marca que intenta asignar a este item no existe";
    public static final String WRONG_CATEGORY_LIST_CREATION_MESSAGE = "La lista de categorías no puede estar vacía, ni puede tener más de 3 categorías";
    public static final String REPEATED_CATEGORIES_MESSAGE = "Hay categorías repetidas";
    public static final String CATEGORY_DOESNT_EXIST_MESSAGE = "La categoría que intenta agregar a este item no existe: ";
    public static final String ITEM_NOT_FOUND_EXCEPTION_MESSAGE = "El item con este id no existe";
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
        fillCategoriesInItems(itemsWithCategories);

        return itemsWithCategories;
    }

    @Override
    public List<Item> getAllByCategoryName(String categoryName, String order) {
        UtilClassDomain.validateOrderingMethod(order);

        List<Item> itemsWithCategories = itemPersistentPort.getItemsByCategoryName(categoryName, order);
        fillCategoriesInItems(itemsWithCategories);

        return itemsWithCategories;
    }

    @Override
    public List<Item> getAllByName(String itemName, String order) {
        UtilClassDomain.validateOrderingMethod(order);

        List<Item> itemsWithCategories=itemPersistentPort.getItemsByName(itemName, order);
        fillCategoriesInItems(itemsWithCategories);

        return itemsWithCategories;
    }

    @Override
    public void updateStockItem(Long itemId, Long amount) {
        if (!existById(itemId)){
            throw new ItemException(ITEM_NOTFOUND_EXCEPTION_MESSAGE);
        }
        if (amount == null || amount<=0){
            throw new IncreaseItemStockException(INCREASE_ITEM_STOCK_EXCEPTION_MESSAGE);
        }
        itemPersistentPort.updateItemStock(itemId, amount);
    }

    @Override
    public boolean existById(Long id) {
        return itemPersistentPort.existById(id);
    }

    @Override
    public boolean isEnoughInStock(Long itemId, Long quantity) {
        if (!existById(itemId)){
            throw new ItemException(ITEM_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return itemPersistentPort.isEnoughInStock(itemId, quantity);
    }

    public boolean areCategoriesValid(List<Long> itemsIds) {
        List<Item> items = itemPersistentPort.getItemsByIds(itemsIds);
        fillCategoriesInItems(items);

        Map<String, Long> categoryCount = items.stream()
                .flatMap(item -> item.getCategories().stream())
                .collect(Collectors.groupingBy(Category::getName, Collectors.counting()));

        return categoryCount.values().stream().noneMatch(count -> count > 3);
    }





    //Internal Class Methods








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
                .orElseThrow(() -> new CategoryException(CATEGORY_DOESNT_EXIST_MESSAGE +category.getName())))
                .toList();
    }

    private Brand findBrand(String name) {
        return brandPersistentPort
                .findByName(name)
                .orElseThrow(() -> new BrandException(BRAND_DONT_EXIST_MESSAGE));
    }
    private void validateList(List<Category> categories) {
        Set<Category> noRepeatedCategory = new HashSet<>(categories);
        if (categories.isEmpty() || categories.size()>3){
            throw new ItemException(WRONG_CATEGORY_LIST_CREATION_MESSAGE);
        }
        if (noRepeatedCategory.size()<categories.size()){
            throw new ItemException(REPEATED_CATEGORIES_MESSAGE);
        }
    }

    private void fillCategoriesInItems(List<Item> listItems) {
        listItems.forEach(item -> item.setCategories(itemCategoryPersistentPort.findCategoriesByItemId(item.getId())));
    }
}
