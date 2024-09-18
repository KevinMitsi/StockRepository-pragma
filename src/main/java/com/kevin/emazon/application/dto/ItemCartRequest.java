package com.kevin.emazon.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartRequest {
    @NotNull
    private List<Long> itemIds;

    private Long categoryToOrder;
    private Long brandToOrder;
}
