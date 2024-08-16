package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import com.kevin.emazon.infraestructure.mapper.ICategoryEntityMapper;
import com.kevin.emazon.infraestructure.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryJpaAdapter implements ICategoryPersistentPort {
    private final ICategoryEntityMapper categoryEntityMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> getCategories() {
        return null;
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
    }

    @Override
    public void deleteCategory(Long id) {

    }
}
