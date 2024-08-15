package com.kevin.emazon.domain.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @NotBlank
    private String name;

    @NotNull
    private Long stockQuantity;

    @NotNull
    private Brand brand;

    @NotNull
    private Set<Category> categories;

}
