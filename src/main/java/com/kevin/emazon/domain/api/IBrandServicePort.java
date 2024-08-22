package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    Page<Brand> getAll(String order, Pageable pageable);

}
