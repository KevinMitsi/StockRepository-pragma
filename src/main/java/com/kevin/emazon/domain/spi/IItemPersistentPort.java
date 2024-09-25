package com.kevin.emazon.domain.spi;

import com.kevin.emazon.domain.model.Item;

import java.util.List;

public interface IItemPersistentPort {
    Item saveItem(Item item);
    List<Item> getItemsByCategoryName(String categoryName, String order, Integer pageNumber, Integer pageSize);
    List<Item> getItemsByBrandName(String brandName, String order, Integer pageNumber, Integer pageSize);
    List<Item> getItemsByName(String itemName, String order, Integer pageNumber, Integer pageSize);

    void updateItemStock(Long itemId, Long amount);
    boolean existById(Long id);
    boolean isEnoughInStock(Long itemId, Long quantity);
    List<Item> getItemsByIds(List<Long> itemsIds);
    List<Item> findByCategoryIdAndItemIds(Long category, List<Long> itemIds);
    List<Item> findByBrandIdAndItemIds(Long brand, List<Long> itemIds);
    List<Item> findByIdAndBrandIdAndItemIds(Long category, Long band, List<Long> itemIds);
    Double getPriceByItemId(Long itemId);
    void reduceStock(Long itemId, Long quantity);

}
