package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.application.handler.IBrandHandler;
import com.kevin.emazon.application.mapper.IBrandDtoMapper;
import com.kevin.emazon.application.util.ListToPageConversor;
import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<BrandDto> getAll(String order, Integer pageNumber, Integer pageSize) {
        List<Brand> brands = brandServicePort.getAll(order, pageNumber, pageSize);
        return ListToPageConversor.convertListIntoPage(convertList(brands),pageNumber,pageSize);
    }

    private List<BrandDto> convertList(List<Brand> all) {
        return all.stream().map(brandDtoMapper::toBrandDto).toList();
    }
}
