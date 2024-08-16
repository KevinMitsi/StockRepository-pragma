package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Brand;

import java.util.Optional;

public interface BrandServicePort {
    Iterable<Brand> getBrands();
    Optional<Brand> getBrand(Long id);
    Brand saveBrand(Brand brand);
    void deleteBrand(Long id);
}
