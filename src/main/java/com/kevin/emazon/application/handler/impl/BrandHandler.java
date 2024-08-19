package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.BrandDto;
import com.kevin.emazon.application.handler.IBrandHandler;
import com.kevin.emazon.application.mapper.IBrandDtoMapper;
import com.kevin.emazon.domain.api.IBrandServicePort;
import lombok.RequiredArgsConstructor;
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
}
