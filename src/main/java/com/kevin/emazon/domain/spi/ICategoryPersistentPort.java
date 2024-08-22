package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.Optional;


public interface ICategoryPersistentPort {
    Page<Category> getCategories(String order, Pageable pageable);
    Optional<Category> getCategory(Long id);

    void saveCategory(Category category);

    boolean existByNameIgnoreCase(String name);
    Optional<Category> findByName(String name);
}
