package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Item;

public interface IItemServicePort {
    void saveItem(Item item);
}
