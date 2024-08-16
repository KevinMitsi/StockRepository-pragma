package com.kevin.emazon.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long stockQuantity;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

//    @ManyToMany
//    @JoinTable(
//            name = "article_category",
//            joinColumns = @JoinColumn(name = "article_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
//    private Set<CategoryEntity> categories;

}
