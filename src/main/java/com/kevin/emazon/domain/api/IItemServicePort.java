package com.kevin.emazon.domain.api;

import com.kevin.emazon.domain.model.Item;

import java.util.List;

public interface IItemServicePort {
    void saveItem(Item item);
    List<Item> getAllByBrandName(String brandName, String order, Integer pageNumber, Integer pageSize);
    List<Item> getAllByCategoryName(String categoryName, String order, Integer pageNumber, Integer pageSize);
    List<Item> getAllByName(String itemName, String order, Integer pageNumber, Integer pageSize);

    void updateStockItem(Long itemId, Long amount);
    boolean existById(Long id);
    boolean isEnoughInStock(Long itemId, Long quantity);
    boolean areCategoriesValid(List<Long> itemsIds);

    List<Item> geItemsInUserCart(List<Long> itemIds, Long categoryToOrder, Long brandToOrder);
}
