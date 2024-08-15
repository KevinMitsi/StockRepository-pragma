package com.kevin.emazon.application.handler;

import com.kevin.emazon.application.dto.request.ItemRequest;
import com.kevin.emazon.application.dto.response.ItemResponse;
import com.kevin.emazon.infraestructure.entity.Item;

import java.util.List;

public interface IItemHandler {
    void saveItemRequestInItems(ItemRequest itemRequest);
    List<ItemResponse> getAllItems();
    ItemResponse getItemById(Long id);
    void updateItem(Item item);

}
