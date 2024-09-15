package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Category;

import java.util.List;
import java.util.Optional;


public interface ICategoryPersistentPort {
    List<Category> getCategories(String order, Integer pageNumber, Integer pageSize);

    void saveCategory(Category category);

    boolean existByNameIgnoreCase(String name);
    Optional<Category> findByName(String name);
}
