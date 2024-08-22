package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.model.ItemCategory;

import java.util.List;

public interface IItemCategoryPersistentPort {
    void saveItemCategory(ItemCategory itemCategory);
    List<Category> findCategoriesByItemId(Long itemId);
}
