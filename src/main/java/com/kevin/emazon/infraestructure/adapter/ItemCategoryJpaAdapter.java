package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Category;
import com.kevin.emazon.domain.model.ItemCategory;
import com.kevin.emazon.domain.spi.IItemCategoryPersistentPort;
import com.kevin.emazon.infraestructure.mapper.ICategoryEntityMapper;
import com.kevin.emazon.infraestructure.mapper.IItemCategoryEntityMapper;
import com.kevin.emazon.infraestructure.repositories.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemCategoryJpaAdapter implements IItemCategoryPersistentPort {
    private final ItemCategoryRepository itemCategoryRepository;
    private final IItemCategoryEntityMapper itemCategoryEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public void saveItemCategory(ItemCategory itemCategory) {
        itemCategoryRepository.save(itemCategoryEntityMapper.itemCategoryToItemCategoryEntity(itemCategory));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findCategoriesByItemId(Long itemId) {
        return itemCategoryRepository.findCategoriesByItemId(itemId).stream().map(categoryEntityMapper::toCategory).toList();
    }

}
