package com.kevin.emazon.infraestructure.mapper;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    CategoryEntity categoryToCategoryEntity(Category category);
    Category categoryEntityToCategory(CategoryEntity categoryEntity);
}
