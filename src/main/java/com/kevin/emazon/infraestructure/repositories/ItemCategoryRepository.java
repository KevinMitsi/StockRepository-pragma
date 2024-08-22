package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
}
