package com.kevin.emazon.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateItemQuantityRequest {
    private Long itemId;
    private Long amountSupplied;

    public UpdateItemQuantityRequest(Long itemId, Long amountSupplied) {
        this.itemId = itemId;
        this.amountSupplied = amountSupplied;
    }

    public UpdateItemQuantityRequest() {
    }
}
