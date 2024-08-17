package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.CategoryDto;
import com.kevin.emazon.application.handler.ICategoryHandler;
import com.kevin.emazon.application.mapper.ICategoryDtoMapper;
import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryHandler implements ICategoryHandler {
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryMapper;
    @Override
    public void saveCategory(CategoryDto category) {
        Category cat = categoryMapper.categoryDtoToCategory(category);
        categoryServicePort.saveCategory(cat);
    }
}
