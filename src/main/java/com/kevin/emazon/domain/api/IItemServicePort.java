package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Item;

import java.util.List;

public interface IItemServicePort {
    void saveItem(Item item);
    List<Item> getAllByBrandName(String brandName, String order);
    List<Item> getAllByCategoryName(String categoryName, String order);
    List<Item> getAllByName(String itemName, String order);

    boolean existById(Long id);
}
