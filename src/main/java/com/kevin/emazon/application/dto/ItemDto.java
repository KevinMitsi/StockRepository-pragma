package com.kevin.emazon.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    @NotNull
    private String name;

    private Double price;

    private Long stockQuantity;

    private BrandDto brand;

    private Set<CategoryDto> categories;


}
