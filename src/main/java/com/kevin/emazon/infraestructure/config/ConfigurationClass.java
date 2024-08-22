package com.kevin.emazon.infraestructure.config;

import com.kevin.emazon.domain.api.IBrandServicePort;
import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.api.IItemServicePort;
import com.kevin.emazon.domain.spi.IBrandPersistentPort;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemCategoryPersistentPort;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.domain.usecase.BrandUseCase;
import com.kevin.emazon.domain.usecase.CategoryUseCase;
import com.kevin.emazon.domain.usecase.ItemUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public ICategoryServicePort categoryServicePort(ICategoryPersistentPort categoryPersistentPort) {
        return new CategoryUseCase(categoryPersistentPort);
    }
    @Bean
    public IBrandServicePort brandServicePort(IBrandPersistentPort brandPersistentPort){
        return new BrandUseCase(brandPersistentPort);
    }
    @Bean
    public IItemServicePort itemServicePort(IItemPersistentPort itemPersistentPort, ICategoryPersistentPort persistentPort, IBrandPersistentPort brandPersistentPort, IItemCategoryPersistentPort itemCategoryPersistentPort){
        return new ItemUseCase(itemPersistentPort, persistentPort, brandPersistentPort, itemCategoryPersistentPort);
    }
}