package com.kevin.emazon.domain.spi;


import com.kevin.emazon.domain.model.Item;

import java.util.Optional;

public interface ItemPersistentPort {
    Iterable<Item> getItems();
    Optional<Item> getItem(Long id);
    Item saveItem(Item item);
    void deleteItem(Long id);
}
