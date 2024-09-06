package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.infraestructure.entity.ItemEntity;
import com.kevin.emazon.infraestructure.mapper.IItemEntityMapper;
import com.kevin.emazon.infraestructure.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemJpaAdapter implements IItemPersistentPort {
    private static final String SORT_BY = "name";
    private static final String SORTING_KEY = "desc";

    private final ItemRepository itemRepository;
    private final IItemEntityMapper itemEntityMapper;
    @Override
    public Item saveItem(Item item) {
        return itemEntityMapper.toItem(itemRepository.save(itemEntityMapper.toItemEntity(item)));
    }

    @Override
    public List<Item> getItemsByCategoryName(String categoryName, String order) {
        Pageable pageable = createPageRequest(order);
        Page<ItemEntity> itemEntities = itemRepository.findAllByItemCategories_Category_NameIgnoreCase(categoryName, pageable);
        return itemEntities.map(itemEntityMapper::toItem).getContent();
    }

    @Override
    public List<Item> getItemsByBrandName(String brandName, String order) {
        Pageable pageable = createPageRequest(order);
        Page<ItemEntity> itemEntities = itemRepository.findAllByBrand_NameIgnoreCase(brandName, pageable);
        return itemEntities.map(itemEntityMapper::toItem).getContent();
    }

    @Override
    public List<Item> getItemsByName(String itemName, String order) {
        Pageable pageable = createPageRequest(order);
        Page<ItemEntity> itemEntities = itemRepository.findAllByNameContainingIgnoreCase(itemName, pageable);
        return itemEntities.map(itemEntityMapper::toItem).getContent();
    }

    @Override
    public boolean existById(Long id) {
        return itemRepository.existsById(id);
    }

    private Pageable createPageRequest(String order) {
        Sort sort = Sort.by(SORT_BY).ascending();
        if (SORTING_KEY.equalsIgnoreCase(order)) {
            sort = sort.descending();
        }
        return PageRequest.of(0, 10, sort);
    }
}
