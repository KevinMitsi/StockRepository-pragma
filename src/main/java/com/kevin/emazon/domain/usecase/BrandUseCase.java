package com.kevin.emazon.domain.usecase;

import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.model.Brand;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.infraestructure.exceptions.BrandException;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistentPort persistentPort;

    public BrandUseCase(IBrandPersistentPort persistentPort) {
        this.persistentPort = persistentPort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (!isValidBrand(brand)){
            throw new BrandException("El nombre o la descripción supera el total de caracteres. Nombre(Max 50) Descripcion(Max 90)");
        }
        persistentPort.saveBrand(brand);

    }

    private boolean isValidBrand(Brand brand){
        if (brand.getName().isBlank() || brand.getDescription().isBlank()){
            throw new BrandException("Ni el nombre ni la descripción pueden ser vacíos");
        }
        return (brand.getName().length() < 50) && (brand.getDescription().length() < 90);
    }
}
