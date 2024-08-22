package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.ItemCategory;

public interface IItemCategoryPersistentPort {
    void saveItemCategory(ItemCategory itemCategory);
}
