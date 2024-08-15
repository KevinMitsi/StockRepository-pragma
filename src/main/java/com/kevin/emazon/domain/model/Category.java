package com.kevin.emazon.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 30, max = 90)
    private String description;
}
