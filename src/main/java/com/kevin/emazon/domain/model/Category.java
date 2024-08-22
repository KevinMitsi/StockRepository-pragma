package com.kevin.emazon.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class Category {
    private Long id;
    private String name;
    private String description;
    private List<Item> items;

    public Category() {
        this.items = new ArrayList<>();
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
    }
}
