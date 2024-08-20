package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.BrandDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandHandler {
    void saveBrand(BrandDto brandDto);
    Page<BrandDto> getAll(String order, Pageable pageable);
}
