package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


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
    public Optional<Category> getCategory(Long id) {
        // not necessary yet
        return categoryPersistentPort.getCategory(id);
    }

    @Override
    public void saveCategory(Category category) {
        if (!isValidCategory(category)){
            throw new CategoryException("El nombre o la descripción supera el total de caracteres. Nombre(Max 50) Descripcion(Max 90)");
        }
        categoryPersistentPort.saveCategory(category);
    }

    private boolean isValidCategory(Category category){
        return category.getName().length() < 50 &&
                category.getDescription().length() < 90;
    }

    @Override
    public void deleteCategory(Long id) {
        // not necessary yet
    }




}
