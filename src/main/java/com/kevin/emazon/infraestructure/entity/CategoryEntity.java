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
public class CategoryEntity {
    public static final String CATEGORY_KEY = "category";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;


    @OneToMany(mappedBy = CATEGORY_KEY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCategoryEntity> itemCategories;


}
