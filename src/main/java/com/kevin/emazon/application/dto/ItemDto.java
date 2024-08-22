package com.kevin.emazon.application.dto;

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
    private String name;

    private Double price;

    private Long stockQuantity;

    private BrandDto brand;

    private Set<CategoryDto> categories;


}
