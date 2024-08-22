package com.kevin.emazon.application.mapper.response;

import com.kevin.emazon.application.dto.response.CategoryResponseDto;
import com.kevin.emazon.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseDtoMapper {

    @Mapping(target = "name", source = "name")
    CategoryResponseDto categoryToCategoryResponseDto(Category category);

}
