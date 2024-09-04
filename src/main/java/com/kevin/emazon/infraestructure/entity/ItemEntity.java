package com.kevin.emazon.infraestructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemEntity {
    private static final String BRAND_KEY = "brand_id";
    private static final String ITEM_KEY = "item";
    private static final boolean ORPHAN_REMOVAL_VALUE = true;
    private static final String ITEM_CATEGORY_COLUMN_NAME = "item_category_id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Long stockQuantity;

    @ManyToOne
    @JoinColumn(name = BRAND_KEY)
    private BrandEntity brand;

    @OneToMany(mappedBy = ITEM_KEY, cascade = CascadeType.ALL, orphanRemoval = ORPHAN_REMOVAL_VALUE)
    @Column(name = ITEM_CATEGORY_COLUMN_NAME)
    private List<ItemCategoryEntity> itemCategories;
}
