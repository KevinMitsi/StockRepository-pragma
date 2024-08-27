package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    List<Brand> getAll(String order);

}
