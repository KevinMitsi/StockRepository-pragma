package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.util.UtilClassDomain;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;

import java.util.List;


public class CategoryUseCase implements ICategoryServicePort {


    private final ICategoryPersistentPort categoryPersistentPort;

    public CategoryUseCase(ICategoryPersistentPort categoryPersistentPort) {
        this.categoryPersistentPort = categoryPersistentPort;
    }


    @Override
    public List<Category> getCategories(String order) {

        UtilClassDomain.validateOrderingMethod(order);

        return categoryPersistentPort.getCategories(order);
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistentPort.existByNameIgnoreCase(category.getName())){
            throw new CategoryException("Categoría ya creada");
        }
        categoryPersistentPort.saveCategory(category);
    }


}
