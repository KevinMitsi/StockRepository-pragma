package com.kevin.emazon.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {

    @Size(min = 10, max = 50)
    @NotBlank
    private String name;

    @Size(min = 10, max = 50)
    @NotBlank
    private String description;
}
