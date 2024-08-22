package com.kevin.emazon.infraestructure.adapter;

import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.spi.IItemPersistentPort;
import com.kevin.emazon.infraestructure.mapper.IItemEntityMapper;
import com.kevin.emazon.infraestructure.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemJpaAdapter implements IItemPersistentPort {
    private final ItemRepository itemRepository;
    private final IItemEntityMapper itemEntityMapper;
    @Override
    public Item saveItem(Item item) {
        return itemEntityMapper.toItem(itemRepository.save(itemEntityMapper.toItemEntity(item)));
    }
}
