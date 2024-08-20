package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandPersistentPort {
    void saveBrand(Brand brand);
    Page<Brand> getAll(String order, Pageable pageable);
}
