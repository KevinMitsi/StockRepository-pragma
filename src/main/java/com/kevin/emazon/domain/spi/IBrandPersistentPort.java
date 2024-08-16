package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Brand;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IBrandPersistentPort {
    Iterable<Brand> getBrands();
    Optional<Brand> getBrand(Long id);
    Brand saveBrand(Brand item);
    void deleteBrand(Long id);
}
