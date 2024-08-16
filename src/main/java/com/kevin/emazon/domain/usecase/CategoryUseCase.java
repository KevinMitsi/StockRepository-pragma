package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;

import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistentPort categoryPersistentPort;
    @Override
    public Iterable<Category> getCategories() {
        return categoryPersistentPort.getCategories();
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoryPersistentPort.getCategory(id);
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistentPort.saveCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
