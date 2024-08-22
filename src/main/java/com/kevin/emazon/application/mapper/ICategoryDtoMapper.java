package com.kevin.emazon.application.mapper;

import com.kevin.emazon.application.dto.CategoryDto;
import com.kevin.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryDtoMapper {

    @Mapping(target = "name", source = "name")
    Category toCategory(CategoryDto categoryDto);

    @Mapping(target = "name", source = "name")
    CategoryDto toCategoryDto(Category category);
}
