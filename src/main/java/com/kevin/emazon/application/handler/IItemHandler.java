package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.ItemDto;
import com.kevin.emazon.application.dto.response.ItemResponseDto;
import com.kevin.emazon.application.dto.response.ItemCartResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IItemHandler {
    void saveItem(ItemDto itemDto);

    Page<ItemResponseDto> getAllByBrandName(String brandName, String order, Integer pageNumber, Integer pageSize);
    Page<ItemResponseDto> getAllByCategoryName(String categoryName, String order, Integer pageNumber, Integer pageSize);
    Page<ItemResponseDto> getAllByName(String itemName, String order, Integer pageNumber, Integer pageSize);

    void updateStockItem(Long itemId, Long amount);

    boolean existById(Long id);
    boolean isEnoughInStock(Long itemId, Long quantity);
    boolean validateCategoryLimit(List<Long> itemsIds);
    List<ItemCartResponse> geItemsInUserCart(List<Long> itemIds, Long categoryToOrder, Long brandToOrder);

}
