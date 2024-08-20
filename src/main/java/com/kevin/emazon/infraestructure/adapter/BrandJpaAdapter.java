package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.infraestructure.entity.BrandEntity;
import com.kevin.emazon.infraestructure.exceptions.BrandException;
import com.kevin.emazon.infraestructure.mapper.IBrandEntityMapper;
import com.kevin.emazon.infraestructure.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        brandRepository.save(brandEntityMapper.brandToBrandEntity(brand));
    }

    @Override
    public Page<Brand> getAll(String order, Pageable pageable) {
        if (order.equalsIgnoreCase("asc")){
            return mapBrandEntityToBrand(brandRepository.findAll(sortPageAscending(pageable)));
        }
        if (order.equalsIgnoreCase("desc")){
            return mapBrandEntityToBrand(brandRepository.findAll(sortPageDescending(pageable)));
        }
        throw new BrandException("metodo de ordenamiento invalido ingrese 'asc' o 'desc'");
    }

    private Pageable sortPageDescending(Pageable pageable) {
        Sort sort = Sort.by("name").descending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Pageable sortPageAscending(Pageable pageable) {
        Sort sort = Sort.by("name").ascending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Page<Brand> mapBrandEntityToBrand(Page<BrandEntity> page){
        return page.map(brandEntityMapper::brandEntityToBrand);
    }
}
