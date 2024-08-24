package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;




public interface ICategoryServicePort {

    Page<Category> getCategories(String order, Pageable pageable);
    void saveCategory(Category category);
}
