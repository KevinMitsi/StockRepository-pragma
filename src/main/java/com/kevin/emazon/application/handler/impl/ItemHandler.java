package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.dto.response.ItemResponseDto;
import com.kevin.emazon.application.handler.IItemHandler;
import com.kevin.emazon.application.mapper.IItemDtoMapper;
import com.kevin.emazon.application.mapper.response.IItemResponseDtoMapper;
import com.kevin.emazon.application.util.ItemCartMapper;
import com.kevin.emazon.application.util.ListToPageConversor;
import com.kevin.emazon.domain.model.Item;
import com.kevin.emazon.domain.api.IItemServicePort;
import com.kevin.emazon.application.dto.response.ItemCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ItemHandler implements IItemHandler {
    public static final String NO_ITEMS_FOUND_MESSAGE = "No hay items con esta categoría";
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

    @Override
    public Double getPriceByItemId(Long itemId) {
        return itemServicePort.getPriceByItemId(itemId);
    }

    @Override
    public List<ItemCartResponse> geItemsInUserCart(List<Long> itemIds, Long categoryToOrder, Long brandToOrder) {
        return itemServicePort.geItemsInUserCart(itemIds,categoryToOrder,brandToOrder)
                .stream()
                .map(ItemCartMapper::toItemCartResponse)
                .toList();
    }

    @Override
    public void reduceStock(Long itemId, Long quantity) {
        itemServicePort.reduceStock(itemId, quantity);
    }

    private List<ItemResponseDto> convertList(List<Item> items) {
        List<ItemResponseDto>itemResponseDtoList = items.stream()
                .map(itemResponseDtoMapper::itemToItemResponseDto)
                .toList();
        if (itemResponseDtoList.isEmpty()){
            throw new NoSuchElementException(NO_ITEMS_FOUND_MESSAGE);
        }
        return itemResponseDtoList;
    }
}