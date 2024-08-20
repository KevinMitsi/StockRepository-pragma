package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.application.handler.IBrandHandler;
import com.kevin.emazon.application.mapper.IBrandDtoMapper;
import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BrandHandler implements IBrandHandler {
    private final IBrandServicePort brandServicePort;
    private final IBrandDtoMapper brandDtoMapper;
    @Override
    public void saveBrand(BrandDto brandDto) {
        brandServicePort.saveBrand(brandDtoMapper.toBrand(brandDto));
    }

    @Override
    public Page<BrandDto> getAll(String order, Pageable pageable) {
        return mapBrandPageToBrandDtoPage(brandServicePort.getAll(order, pageable));
    }

    private Page<BrandDto> mapBrandPageToBrandDtoPage(Page<Brand> page) {
        return page.map(brandDtoMapper::toBrandDto);
    }
}
