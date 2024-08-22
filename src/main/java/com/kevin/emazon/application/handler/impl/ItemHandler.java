package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.handler.IItemHandler;
import com.kevin.emazon.application.mapper.ICategoryDtoMapper;
import com.kevin.emazon.application.mapper.IItemDtoMapper;
import com.kevin.emazon.domain.api.IItemServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemHandler implements IItemHandler {
    private final IItemServicePort itemServicePort;
    private final IItemDtoMapper itemDtoMapper;
    private final ICategoryDtoMapper categoryDtoMapper;


    @Override
    public void saveItem(ItemDto itemDto) {
        itemServicePort.saveItem(itemDtoMapper.toItem(itemDto));
    }
}
