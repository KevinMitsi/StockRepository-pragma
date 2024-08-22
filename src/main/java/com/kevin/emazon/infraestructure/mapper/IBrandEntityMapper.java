package com.kevin.emazon.infraestructure.mapper;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.infraestructure.entity.BrandEntity;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    BrandEntity brandToBrandEntity(Brand brand);

    @Mapping(target = "name", source = "name")
    Brand brandEntityToBrand(BrandEntity brandEntity);
}
