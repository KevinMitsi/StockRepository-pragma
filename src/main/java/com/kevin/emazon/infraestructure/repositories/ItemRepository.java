package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Page<ItemEntity> findAllByBrand_NameIgnoreCase(String brandName, Pageable pageable);

    Page<ItemEntity> findAllByItemCategories_Category_NameIgnoreCase(String categoryName, Pageable pageable);

    Page<ItemEntity> findAllByNameContainingIgnoreCase(String itemName, Pageable pageable);

    @Query("SELECT i.stockQuantity FROM ItemEntity i WHERE i.id = :itemId")
    Long findStockQuantityByItemId(Long itemId);

    List<ItemEntity> findByIdIn(List<Long> itemIds);

    @Query("SELECT i FROM ItemEntity i JOIN i.itemCategories c WHERE c.category.id = :categoryId AND i.id IN :itemIds")
    List<ItemEntity> findByCategoryIdAndItemIds(@Param("categoryId") Long categoryId, @Param("itemIds") List<Long> itemIds);

    @Query("SELECT i FROM ItemEntity i WHERE i.brand.id = :brandId AND i.id IN :itemIds")
    List<ItemEntity> findByBrandIdAndItemIds(@Param("brandId") Long brandId, @Param("itemIds") List<Long> itemIds);

    @Query("SELECT i FROM ItemEntity i JOIN i.itemCategories c WHERE c.category.id = :categoryId AND i.brand.id = :brandId AND i.id IN :itemIds")
    List<ItemEntity> findByCategoryIdAndBrandIdAndItemIds(@Param("categoryId") Long categoryId, @Param("brandId") Long brandId, @Param("itemIds") List<Long> itemIds);

    @Query("SELECT i.price FROM ItemEntity i WHERE i.id = :itemId")
    Double findPriceById(@Param("itemId") Long itemId);

}
