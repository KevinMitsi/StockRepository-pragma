package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ICategoryPersistentPort {
    Iterable<Category> getCategories();
    Optional<Category> getCategory(Long id);
    void saveCategory(Category category);
    void deleteCategory(Long id);
}
