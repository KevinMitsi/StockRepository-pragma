package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.infraestructure.mapper.IBrandEntityMapper;
import com.kevin.emazon.infraestructure.repositories.BrandRepository;
import com.kevin.emazon.infraestructure.util.PageableCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BrandJpaAdapter implements IBrandPersistentPort {
    private final BrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));
    }

    @Override
    public boolean existByNameIgnoreCase(String name) {
        return brandRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByNameIgnoreCase(name).map(brandEntityMapper::brandEntityToBrand);
    }


    @Override
    public List<Brand> getAll(String order) {
        return brandRepository.findAll(PageableCreator.createPageable(order))
                .map(brandEntityMapper::brandEntityToBrand)
                .getContent();
    }

}
