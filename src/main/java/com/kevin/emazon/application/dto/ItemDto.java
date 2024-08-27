package com.kevin.emazon.application.dto;

import com.kevin.emazon.application.util.ConstantUtilClass;
import jakarta.validation.constraints.*;
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

    @NotNull(message = ConstantUtilClass.ItemErrorMsg.NN_NAME)
    @NotBlank(message = ConstantUtilClass.ItemErrorMsg.NB_NAME)
    @Size(message = ConstantUtilClass.ItemErrorMsg.S_NAME)
    private String name;

    @NotNull(message = ConstantUtilClass.ItemErrorMsg.NN_PRICE)
    @Min(message = ConstantUtilClass.ItemErrorMsg.MIN_PRICE, value = 500)
    @Max(message = ConstantUtilClass.ItemErrorMsg.MAX_PRICE,value = 20000000)
    private Double price;

    @Min(message = ConstantUtilClass.ItemErrorMsg.MIN_STOCK, value = 0)
    @NotNull(message = ConstantUtilClass.ItemErrorMsg.NN_STOCK)
    private Long stockQuantity;

    @NotNull(message = ConstantUtilClass.ItemErrorMsg.NN_BRAND)
    private BrandDto brand;

    @NotNull(message = ConstantUtilClass.ItemErrorMsg.NN_CATEGORIES)
    @Size(message = ConstantUtilClass.ItemErrorMsg.SIZE_CATEGORIES, max = 3)
    private Set<CategoryDto> categories;

}
