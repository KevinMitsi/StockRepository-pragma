package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.request.CategoryRequestDto;
import com.kevin.emazon.domain.model.Category;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto category);

}
