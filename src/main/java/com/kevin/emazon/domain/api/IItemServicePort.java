package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Item;

import java.util.Optional;

public interface IItemServicePort {
    Iterable<Item> getItems();
    Optional<Item> getItem(Long id);
    Item saveItem(Item item);
    void deleteItem(Long id);
}
