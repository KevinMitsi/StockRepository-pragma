package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface ICategoryHandler {

    Page<CategoryDto> getAllCategories(String order, Integer pageNumber, Integer pageSize);
    void saveCategory(CategoryDto category);

}
