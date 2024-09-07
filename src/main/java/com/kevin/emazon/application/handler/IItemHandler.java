package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.dto.response.ItemResponseDto;
import org.springframework.data.domain.Page;


public interface IItemHandler {
    void saveItem(ItemDto itemDto);

    Page<ItemResponseDto> getAllByBrandName(String brandName, String order);
    Page<ItemResponseDto> getAllByCategoryName(String categoryName, String order);
    Page<ItemResponseDto> getAllByName(String itemName, String order);

    void updateStockItem(Long itemId, Long amount);

    boolean existById(Long id);
}
