package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.util.UtilClassDomain;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;

import java.util.List;


public class CategoryUseCase implements ICategoryServicePort {


    public static final String ALREADY_CREATED_CATEGORY_MESSAGE = "Categoría ya creada";
    private final ICategoryPersistentPort categoryPersistentPort;

    public CategoryUseCase(ICategoryPersistentPort categoryPersistentPort) {
        this.categoryPersistentPort = categoryPersistentPort;
    }


    @Override
    public List<Category> getCategories(String order, Integer pageNumber, Integer pageSize) {

        UtilClassDomain.validateOrderingMethod(order);

        return categoryPersistentPort.getCategories(order, pageNumber, pageSize);
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistentPort.existByNameIgnoreCase(category.getName())){
            throw new CategoryException(ALREADY_CREATED_CATEGORY_MESSAGE);
        }
        categoryPersistentPort.saveCategory(category);
    }


}
