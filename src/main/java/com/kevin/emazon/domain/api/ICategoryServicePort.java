package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Category;

import java.util.List;


public interface ICategoryServicePort {

    List<Category> getCategories(String order, Integer pageNumber, Integer pageSize);
    void saveCategory(Category category);
}
