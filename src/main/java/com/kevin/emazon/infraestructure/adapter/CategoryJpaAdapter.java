package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.spi.ICategoryPersistentPort;
import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import com.kevin.emazon.infraestructure.exceptions.CategoryException;
import com.kevin.emazon.infraestructure.mapper.ICategoryEntityMapper;
import com.kevin.emazon.infraestructure.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryJpaAdapter implements ICategoryPersistentPort {
    private final ICategoryEntityMapper categoryEntityMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> getCategories(String order, Pageable pageable) {
        if (order.equalsIgnoreCase("asc")){
            return mapCategoryEntityToCategory(categoryRepository.findAll(sortPageAscending(pageable)));
        }
        if (order.equalsIgnoreCase("desc")){
            return mapCategoryEntityToCategory(categoryRepository.findAll(sortPageDescending(pageable)));
        }
        throw new CategoryException("metodo de ordenamiento invalido ingrese 'asc' o 'desc'");
    }

    private Pageable sortPageDescending(Pageable pageable) {
        Sort sort = Sort.by("name").descending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Pageable sortPageAscending(Pageable pageable) {
        Sort sort = Sort.by("name").ascending();
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }

    private Page<Category>mapCategoryEntityToCategory(Page<CategoryEntity> page){
        return page.map(categoryEntityMapper::categoryEntityToCategory);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        // not necessary yet
        return Optional.empty();
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new CategoryException("Categoría ya creada");
        }
        categoryRepository.save(categoryEntityMapper.categoryToCategoryEntity(category));
    }

    @Override
    public void deleteCategory(Long id) {
        // not necessary yet
    }



}
