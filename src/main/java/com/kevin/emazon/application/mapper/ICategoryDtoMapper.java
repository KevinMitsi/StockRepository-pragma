package com.kevin.emazon.application.mapper;

import com.kevin.emazon.application.dto.request.CategoryRequestDto;
import com.kevin.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryDtoMapper {
    Category categoryDtoToCategory(CategoryRequestDto categoryRequestDto);
}
