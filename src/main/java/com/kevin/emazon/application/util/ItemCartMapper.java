package com.kevin.emazon.application.util;

import com.kevin.emazon.application.dto.response.ItemCartResponse;
import com.kevin.emazon.domain.model.Item;

public class ItemCartMapper {
    private ItemCartMapper(){}

    public static ItemCartResponse toItemCartResponse(Item item){
        ItemCartResponse cartResponse = new ItemCartResponse();
        cartResponse.setItemId(item.getId());
        cartResponse.setQuantity(item.getStockQuantity());
        cartResponse.setItemName(item.getName());
        cartResponse.setBrandId(item.getBrand().getId());
        return cartResponse;
    }
}
