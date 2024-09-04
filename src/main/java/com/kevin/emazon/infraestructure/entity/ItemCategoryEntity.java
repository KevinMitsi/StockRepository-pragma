package com.kevin.emazon.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemCategoryEntity {
    private static final String ITEM_KEY = "item_id";
    private static final String CATEGORY_COLUMN_NAME = "category_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ITEM_KEY, nullable = false)
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = CATEGORY_COLUMN_NAME, nullable = false)
    private CategoryEntity category;
}
