package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Page<ItemEntity> findAllByBrand_NameIgnoreCase(String brandName, Pageable pageable);

    Page<ItemEntity> findAllByItemCategories_Category_NameIgnoreCase(String categoryName, Pageable pageable);

    Page<ItemEntity> findAllByNameContainingIgnoreCase(String itemName, Pageable pageable);

    @Query("SELECT i.stockQuantity FROM ItemEntity i WHERE i.id = :itemId")
    Long findStockQuantityByItemId(Long itemId);

}
