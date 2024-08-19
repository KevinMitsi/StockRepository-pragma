package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Brand;

public interface IBrandPersistentPort {
    void saveBrand(Brand brand);
}
