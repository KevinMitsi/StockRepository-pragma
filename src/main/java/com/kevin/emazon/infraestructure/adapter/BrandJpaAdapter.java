package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.infraestructure.entity.BrandEntity;
import com.kevin.emazon.infraestructure.mapper.IBrandEntityMapper;
import com.kevin.emazon.infraestructure.repositories.BrandRepository;
import com.kevin.emazon.infraestructure.util.PageableCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BrandJpaAdapter implements IBrandPersistentPort {
    private final BrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    @Override
    @Transactional
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existByNameIgnoreCase(String name) {
        return brandRepository.existsByNameIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByNameIgnoreCase(name).map(brandEntityMapper::brandEntityToBrand);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Brand> getAll(String order, Integer pageNumber, Integer pageSize) {
        Page<BrandEntity> brandEntities = brandRepository.findAll(PageableCreator.createPageable(order,pageNumber, pageSize));

        return brandEntities
                .map(brandEntityMapper::brandEntityToBrand)
                .getContent();
    }

}
