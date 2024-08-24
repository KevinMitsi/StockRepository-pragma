package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Category;

import java.util.List;


public interface ICategoryServicePort {

    List<Category> getCategories(String order);
    void saveCategory(Category category);
}
