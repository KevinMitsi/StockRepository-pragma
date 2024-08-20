package com.kevin.emazon.application.mapper;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandDtoMapper {


    Brand toBrand(BrandDto brandDto);
    BrandDto toBrandDto(Brand brand);
}
