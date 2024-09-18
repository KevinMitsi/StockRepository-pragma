package com.kevin.emazon.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCartResponse {
    private Long itemId;
    private String itemName;
    private Long brandId;

    private Long quantity;
    public ItemCartResponse() {
        //For frameworks etc
    }
}
