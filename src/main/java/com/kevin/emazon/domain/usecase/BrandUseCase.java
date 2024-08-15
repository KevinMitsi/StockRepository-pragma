package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.BrandPersistentPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class BrandUseCase implements IBrandServicePort {
    private final BrandPersistentPort _I_brandPersistentPort;
    @Override
    public Iterable<Brand> getBrands() {
        return _I_brandPersistentPort.getBrands();
    }

    @Override
    public Optional<Brand> getBrand(Long id) {
        return _I_brandPersistentPort.getBrand(id);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return _I_brandPersistentPort.saveBrand(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        _I_brandPersistentPort.deleteBrand(id);
    }
}
