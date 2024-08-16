package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.BrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;
@AllArgsConstructor

public class BrandUseCase implements BrandServicePort {
    private final IBrandPersistentPort brandPersistentPort;
    @Override
    public Iterable<Brand> getBrands() {
        return brandPersistentPort.getBrands();
    }

    @Override
    public Optional<Brand> getBrand(Long id) {
        return brandPersistentPort.getBrand(id);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandPersistentPort.saveBrand(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandPersistentPort.deleteBrand(id);
    }
}
