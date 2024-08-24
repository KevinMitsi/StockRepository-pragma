package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public class CategoryUseCase implements ICategoryServicePort {


    private final ICategoryPersistentPort categoryPersistentPort;

    public CategoryUseCase(ICategoryPersistentPort categoryPersistentPort) {
        this.categoryPersistentPort = categoryPersistentPort;
    }


    @Override
    public Page<Category> getCategories(String order, Pageable pageable) {
        return categoryPersistentPort.getCategories(order,pageable);
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistentPort.existByNameIgnoreCase(category.getName())){
            throw new CategoryException("Categoría ya creada");
        }
        categoryPersistentPort.saveCategory(category);
    }


}
