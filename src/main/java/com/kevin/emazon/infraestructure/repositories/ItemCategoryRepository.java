package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.CategoryEntity;
import com.kevin.emazon.infraestructure.entity.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategoryEntity, Long> {
    @Query("SELECT c FROM CategoryEntity c JOIN ItemCategoryEntity ic ON c.id = ic.category.id WHERE ic.item.id = :itemId")
    List<CategoryEntity> findCategoriesByItemId(Long itemId);
}
