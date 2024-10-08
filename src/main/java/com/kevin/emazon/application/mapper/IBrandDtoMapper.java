package com.kevin.emazon.application.mapper;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandDtoMapper {


    @Mapping(target = "name", source = "name")
    Brand toBrand(BrandDto brandDto);
    @Mapping(target = "name", source = "name")
    BrandDto toBrandDto(Brand brand);
}
