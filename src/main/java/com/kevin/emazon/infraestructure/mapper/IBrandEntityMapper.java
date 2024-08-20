package com.kevin.emazon.infraestructure.mapper;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.infraestructure.entity.BrandEntity;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {
    BrandEntity brandToBrandEntity(Brand brand);
    Brand brandEntityToBrand(BrandEntity brandEntity);
}
