package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.CategoryDto;
import com.kevin.emazon.application.handler.ICategoryHandler;
import com.kevin.emazon.application.mapper.ICategoryDtoMapper;
import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryHandler implements ICategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryMapper;

    @Override
    public Page<CategoryDto> getAllCategories(String order, Pageable pageable) {
        return mapCategoryPageToCategoryDtoPage(categoryServicePort.getCategories(order, pageable));
    }

    private Page<CategoryDto> mapCategoryPageToCategoryDtoPage(Page<Category> categories) {
        return categories.map(categoryMapper::toCategoryDto);
    }

    @Override
    public void saveCategory(CategoryDto category) {
        categoryServicePort.saveCategory(categoryMapper.toCategory(category));
    }
}
