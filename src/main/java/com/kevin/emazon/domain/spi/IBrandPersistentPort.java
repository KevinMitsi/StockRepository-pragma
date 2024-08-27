package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandPersistentPort {
    void saveBrand(Brand brand);
    boolean existByNameIgnoreCase(String name);
    Optional<Brand>findByName(String name);
    List<Brand> getAll(String order);

}
