package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Item;

public interface IItemPersistentPort {
    Item saveItem(Item item);
}
