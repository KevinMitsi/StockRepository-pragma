package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.BrandDto;
import org.springframework.data.domain.Page;

public interface IBrandHandler {

    void saveBrand(BrandDto brandDto);
    Page<BrandDto> getAll(String order);
}
