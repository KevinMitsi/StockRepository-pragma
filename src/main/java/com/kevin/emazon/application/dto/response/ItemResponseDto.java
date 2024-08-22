package com.kevin.emazon.application.dto.response;

import com.kevin.emazon.application.dto.BrandDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {
    private String name;

    private Double price;

    private Long stockQuantity;

    private BrandDto brand;

    private Set<CategoryResponseDto> categories;

}
