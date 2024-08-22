package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBrandPersistentPort {
    void saveBrand(Brand brand);
    boolean existByNameIgnoreCase(String name);
    Optional<Brand>findByName(String name);
    Page<Brand> getAll(String order, Pageable pageable);

}
