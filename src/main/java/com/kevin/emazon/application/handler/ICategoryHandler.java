package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryHandler {

    Page<CategoryDto> getAllCategories(String order, Pageable pageable);
    void saveCategory(CategoryDto category);

}
