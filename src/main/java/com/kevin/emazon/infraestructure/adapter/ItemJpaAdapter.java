package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.infraestructure.entity.ItemEntity;
import com.kevin.emazon.infraestructure.mapper.IItemEntityMapper;
import com.kevin.emazon.infraestructure.repositories.ItemRepository;
import com.kevin.emazon.infraestructure.util.PageableCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemJpaAdapter implements IItemPersistentPort {

    private final ItemRepository itemRepository;
    private final IItemEntityMapper itemEntityMapper;
    @Override
    @Transactional
    public Item saveItem(Item item) {
        return itemEntityMapper.toItem(itemRepository.save(itemEntityMapper.toItemEntity(item)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByCategoryName(String categoryName, String order, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageableCreator.createPageable(order, pageNumber, pageSize);
        Page<ItemEntity> itemEntities = itemRepository.findAllByItemCategories_Category_NameIgnoreCase(categoryName, pageable);
        return itemEntities.map(itemEntityMapper::toItem).getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByBrandName(String brandName, String order, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageableCreator.createPageable(order, pageNumber, pageSize);
        Page<ItemEntity> itemEntities = itemRepository.findAllByBrand_NameIgnoreCase(brandName, pageable);
        return itemEntities.map(itemEntityMapper::toItem).getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getItemsByName(String itemName, String order, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageableCreator.createPageable(order, pageNumber, pageSize);
        Page<ItemEntity> itemEntities = itemRepository.findAllByNameContainingIgnoreCase(itemName, pageable);
        return itemEntities.map(itemEntityMapper::toItem).getContent();
    }

    @Override
    @Transactional
    public void updateItemStock(Long itemId, Long amount) {
        ItemEntity item = itemRepository.findById(itemId).orElseThrow();
        item.setStockQuantity(item.getStockQuantity()+amount);
        itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        return itemRepository.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isEnoughInStock(Long itemId, Long quantity) {
        return itemRepository.findStockQuantityByItemId(itemId) >= quantity;
    }

    @Transactional(readOnly = true)
    public List<Item> getItemsByIds(List<Long> itemsIds) {
        return itemRepository.findByIdIn(itemsIds).stream().map(itemEntityMapper::toItem).toList();
    }

}
