package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.domain.util.UtilClassDomain;
import com.kevin.emazon.infraestructure.exceptions.BrandException;


import java.util.List;

public class BrandUseCase implements IBrandServicePort {
    public static final String ALREADY_CREATED_BRAND_MESSAGE = "Esta marca ya existe";
    private final IBrandPersistentPort persistentPort;

    public BrandUseCase(IBrandPersistentPort persistentPort) {
        this.persistentPort = persistentPort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (persistentPort.existByNameIgnoreCase(brand.getName())){
            throw new BrandException(ALREADY_CREATED_BRAND_MESSAGE);
        }
        persistentPort.saveBrand(brand);
    }


    @Override
    public List<Brand> getAll(String order, Integer pageNumber, Integer pageSize) {

        UtilClassDomain.validateOrderingMethod(order);

        return persistentPort.getAll(order, pageNumber, pageSize);
    }
}
