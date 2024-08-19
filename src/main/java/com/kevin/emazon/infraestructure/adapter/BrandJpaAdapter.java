package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.mapper.IBrandEntityMapper;
import com.kevin.emazon.infraestructure.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BrandJpaAdapter implements IBrandPersistentPort {
    private final BrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    @Override
    public void saveBrand(Brand brand) {
        if (brandRepository.existsByNameIgnoreCase(brand.getName())){
            throw new BrandException("Esta categoria ya existe");
        }
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }
}
