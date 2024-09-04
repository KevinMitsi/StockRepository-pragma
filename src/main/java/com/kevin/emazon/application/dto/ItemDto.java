package com.kevin.emazon.application.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static com.kevin.emazon.application.util.ConstantUtilClass.ItemErrorMsg.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private static final int MIN_STOCK_QUANTITY_VALUE = 0;
    private static final int MAX_PRICE_VALUE = 20000000;
    private static final int MIN_PRICE_VALUE = 500;
    private static final int MAX_CATEGORIES_AMOUNT = 3;


    @NotNull(message = NN_NAME)
    @NotBlank(message = NB_NAME)
    @Size(message = S_NAME)
    private String name;

    @NotNull(message = NN_PRICE)
    @Min(message = MIN_PRICE, value = MIN_PRICE_VALUE)
    @Max(message = MAX_PRICE,value = MAX_PRICE_VALUE)
    private Double price;

    @Min(message = MIN_STOCK, value = MIN_STOCK_QUANTITY_VALUE)
    @NotNull(message = NN_STOCK)
    private Long stockQuantity;

    @NotNull(message = NN_BRAND)
    private BrandDto brand;

    @NotNull(message = NN_CATEGORIES)
    @Size(message = SIZE_CATEGORIES, max = MAX_CATEGORIES_AMOUNT)
    private Set<CategoryDto> categories;

}
