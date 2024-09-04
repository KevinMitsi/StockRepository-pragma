package com.kevin.emazon.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BrandEntity {
    public static final String BRAND_KEY = "brand";
    public static final String ITEMS_COLUMN_NAME = "item_category_id";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = BRAND_KEY)
    @Column(name = ITEMS_COLUMN_NAME)
    private List<ItemEntity> items;

}
