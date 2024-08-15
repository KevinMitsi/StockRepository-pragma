package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Brand;

import java.util.Optional;

public interface BrandPersistentPort {
    Iterable<Brand> getBrands();
    Optional<Brand> getBrand(Long id);
    Brand saveBrand(Brand item);
    void deleteBrand(Long id);
}
