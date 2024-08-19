package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByNameIgnoreCase(String name);
}
