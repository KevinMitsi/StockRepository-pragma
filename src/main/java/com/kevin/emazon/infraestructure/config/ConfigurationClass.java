package com.kevin.emazon.infraestructure.config;

import com.kevin.emazon.domain.api.ICategoryServicePort;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.domain.usecase.CategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @Bean
    public ICategoryServicePort categoryServicePort(ICategoryPersistentPort categoryPersistentPort) {
        return new CategoryUseCase(categoryPersistentPort);
    }
}