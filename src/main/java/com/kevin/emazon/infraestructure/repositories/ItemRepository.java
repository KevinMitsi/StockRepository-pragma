package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Page<ItemEntity> findAllByBrand_NameIgnoreCase(String brandName, Pageable pageable);

    Page<ItemEntity> findAllByItemCategories_Category_NameIgnoreCase(String categoryName, Pageable pageable);

    Page<ItemEntity> findAllByNameContainingIgnoreCase(String itemName, Pageable pageable);

    List<ItemEntity> findByIdIn(List<Long> itemIds);
}
