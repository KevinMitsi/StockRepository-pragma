package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.dto.response.ItemResponseDto;
import com.kevin.emazon.application.handler.IItemHandler;
import com.kevin.emazon.application.mapper.IItemDtoMapper;
import com.kevin.emazon.application.mapper.response.IItemResponseDtoMapper;
import com.kevin.emazon.application.util.ListToPageConversor;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.api.IItemServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Page<ItemResponseDto> getAllByBrandName(String brandName, String order, Integer pageNumber, Integer pageSize) {
        return ListToPageConversor.convertListIntoPage(convertList(itemServicePort.getAllByBrandName(brandName, order, pageNumber, pageSize)),pageNumber,pageSize);
    }
    @Override
    public Page<ItemResponseDto> getAllByCategoryName(String categoryName, String order, Integer pageNumber, Integer pageSize) {
        return ListToPageConversor.convertListIntoPage(convertList(itemServicePort.getAllByCategoryName(categoryName, order, pageNumber,pageSize)),pageNumber,pageSize);
    }

    @Override
    public Page<ItemResponseDto> getAllByName(String itemName,String order, Integer pageNumber, Integer pageSize) {
        return ListToPageConversor.convertListIntoPage(convertList(itemServicePort.getAllByName(itemName, order, pageNumber, pageSize)),pageNumber, pageSize);
    }

    @Override
    public void updateStockItem(Long itemId, Long amount) {
        itemServicePort.updateStockItem(itemId, amount);
    }

    @Override
    public boolean existById(Long id) {
        return itemServicePort.existById(id);
    }

    @Override
    public boolean isEnoughInStock(Long itemId, Long quantity) {
        return itemServicePort.isEnoughInStock(itemId, quantity);
    }

    public boolean validateCategoryLimit(List<Long> itemsIds) {
        return itemServicePort.areCategoriesValid(itemsIds);
    }

    private List<ItemResponseDto> convertList(List<Item> items) {
        List<ItemResponseDto>itemResponseDtoList = items.stream()
                .map(itemResponseDtoMapper::itemToItemResponseDto)
                .toList();
        if (itemResponseDtoList.isEmpty()){
            throw new NoSuchElementException("No hay items con esta categoría");
        }
        return itemResponseDtoList;
    }
}