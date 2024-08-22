package com.kevin.emazon.infraestructure.repositories;

import com.kevin.emazon.infraestructure.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
