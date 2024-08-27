package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.domain.util.UtilClassDomain;
import com.kevin.emazon.infraestructure.exceptions.BrandException;


import java.util.List;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistentPort persistentPort;

    public BrandUseCase(IBrandPersistentPort persistentPort) {
        this.persistentPort = persistentPort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (persistentPort.existByNameIgnoreCase(brand.getName())){
            throw new BrandException("Esta marca ya existe");
        }
        persistentPort.saveBrand(brand);
    }


    @Override
    public List<Brand> getAll(String order) {

        UtilClassDomain.validateOrderingMethod(order);

        return persistentPort.getAll(order);
    }
}
