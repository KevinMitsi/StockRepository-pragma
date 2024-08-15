package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryServicePort categoryPersistentPort;
    @Override
    public Iterable<Category> getCategories() {
        return null;
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }

    @Override
    public Category saveCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
