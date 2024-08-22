package com.kevin.emazon.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {
    @NotNull(message = "El nombre de la marca no puede ser null")
    private String name;

    @NotNull(message = "La descripción no puede ser nula")
    private String description;
}
