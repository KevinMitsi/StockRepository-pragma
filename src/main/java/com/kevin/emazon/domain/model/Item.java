package com.kevin.emazon.domain.model;



import lombok.*;

import java.util.*;


@Setter
@Getter
public class Item {

    private Long id;

    private String name;

    private Double price;

    private Long stockQuantity;

    private Brand brand;

    List<Category> categories;

    public Item() {
        this.categories = new ArrayList<>();
    }

    public Item(Long id, String name, Double price, Long stockQuantity, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.brand = brand;
        this.categories = new ArrayList<>();
    }


}
