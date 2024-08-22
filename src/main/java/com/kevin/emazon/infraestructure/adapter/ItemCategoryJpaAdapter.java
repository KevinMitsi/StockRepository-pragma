package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.ItemCategory;
import com.kevin.emazon.domain.spi.IItemCategoryPersistentPort;
import com.kevin.emazon.infraestructure.mapper.IItemCategoryEntityMapper;
import com.kevin.emazon.infraestructure.repositories.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemCategoryJpaAdapter implements IItemCategoryPersistentPort {
    private final ItemCategoryRepository itemCategoryRepository;
    private final IItemCategoryEntityMapper itemCategoryEntityMapper;
    @Override
    public void saveItemCategory(ItemCategory itemCategory) {
        itemCategoryRepository.save(itemCategoryEntityMapper.itemCategoryToItemCategoryEntity(itemCategory));
    }
}
