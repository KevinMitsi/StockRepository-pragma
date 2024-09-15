package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.CategoryDto;
import com.kevin.emazon.application.handler.ICategoryHandler;
import com.kevin.emazon.application.mapper.ICategoryDtoMapper;
import com.kevin.emazon.application.util.ListToPageConversor;
import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryMapper;

    @Override
    public Page<CategoryDto> getAllCategories(String order, Integer pageNumber, Integer pageSize) {
        return ListToPageConversor.convertListIntoPage(convertList(categoryServicePort.getCategories(order, pageNumber, pageSize)),pageNumber,pageSize);
    }

    @Override
    public void saveCategory(CategoryDto category) {
        categoryServicePort.saveCategory(categoryMapper.toCategory(category));
    }

    private List<CategoryDto> convertList(List<Category> categoriesList){
        return categoriesList.stream().map(categoryMapper::toCategoryDto).toList();
    }
}
