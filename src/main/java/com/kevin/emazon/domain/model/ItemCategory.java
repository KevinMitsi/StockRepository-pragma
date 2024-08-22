package com.kevin.emazon.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCategory {
    private Long id;
    private Item item;
    private Category category;

    public ItemCategory(Long id, Item item, Category category) {
        this.id = id;
        this.item = item;
        this.category = category;
    }

    public ItemCategory() {

    }
}
