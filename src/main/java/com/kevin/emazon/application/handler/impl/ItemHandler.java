package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.dto.response.ItemResponseDto;
import com.kevin.emazon.application.handler.IItemHandler;
import com.kevin.emazon.application.mapper.IItemDtoMapper;
import com.kevin.emazon.application.mapper.response.IItemResponseDtoMapper;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.api.IItemServicePort;
import com.kevin.emazon.infraestructure.exceptions.ItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemHandler implements IItemHandler {
    private final IItemServicePort itemServicePort;
    private final IItemDtoMapper itemDtoMapper;
    private final IItemResponseDtoMapper itemResponseDtoMapper;

    @Override
    public void saveItem(ItemDto itemDto) {
        itemServicePort.saveItem(itemDtoMapper.toItem(itemDto));
    }

    @Override
    public Page<ItemResponseDto> getAllByBrandName(String brandName, String order) {

        List<ItemResponseDto> itemDtos = convertList(itemServicePort.getAllByBrandName(brandName, order));

        return new PageImpl<>(itemDtos, PageRequest.of(0, itemDtos.size()), 10);
    }
    @Override
    public Page<ItemResponseDto> getAllByCategoryName(String categoryName, String order) {

        List<ItemResponseDto> itemDtos = convertList(itemServicePort.getAllByCategoryName(categoryName, order));

        return new PageImpl<>(itemDtos, PageRequest.of(0, itemDtos.size()), 10);
    }
    @Override
    public Page<ItemResponseDto> getAllByName(String itemName, String order) {

        List<ItemResponseDto> itemDtos = convertList(itemServicePort.getAllByName(itemName, order));

        return new PageImpl<>(itemDtos, PageRequest.of(0, itemDtos.size()), 10);
    }
    private List<ItemResponseDto> convertList(List<Item> items) {
        List<ItemResponseDto>itemResponseDtoList = items.stream()
                .map(itemResponseDtoMapper::itemToItemResponseDto)
                .toList();
        if (itemResponseDtoList.isEmpty()){
            throw new ItemException("No hay items con esta categoría");
        }
        return itemResponseDtoList;
    }
}