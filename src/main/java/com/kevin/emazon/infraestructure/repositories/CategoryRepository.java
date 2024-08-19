package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByNameIgnoreCase(String name);
    @NonNull
    Page<CategoryEntity> findAll(@NonNull Pageable pageable);
}
