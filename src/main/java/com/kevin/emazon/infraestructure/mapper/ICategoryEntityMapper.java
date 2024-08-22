package com.kevin.emazon.infraestructure.mapper;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    @Mapping(target = "name", source = "name")
    CategoryEntity toCategoryEntity(Category category);

    @Mapping(target = "name", source = "name")
    Category toCategory(CategoryEntity categoryEntity);
}
