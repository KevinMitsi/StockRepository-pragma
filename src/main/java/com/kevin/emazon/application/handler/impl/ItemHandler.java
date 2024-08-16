package com.kevin.emazon.application.handler.impl;

import com.kevin.emazon.application.dto.request.ItemRequest;
import com.kevin.emazon.application.dto.response.ItemResponse;
import com.kevin.emazon.application.handler.IItemHandler;
import com.kevin.emazon.infraestructure.entity.Item;

import java.util.List;

public class ItemHandler implements IItemHandler {
    @Override
    public void saveItemRequestInItems(ItemRequest itemRequest) {

    }

    @Override
    public List<ItemResponse> getAllItems() {
        return null;
    }

    @Override
    public ItemResponse getItemById(Long id) {
        return null;
    }

    @Override
    public void updateItem(Item item) {

    }
}
