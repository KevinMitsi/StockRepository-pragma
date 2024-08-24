package com.kevin.emazon.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotNull(message = "NullCategoryNameException: El nombre no debe ser nulo")
    @NotBlank(message = "BlankCategoryNameException: El nombre no debe estar vacío ni lleno de espacios")
    @Size(message = "WrongSizeCategoryNameException: El nombre debe estar entre los 5 y los 50 caracteres", min = 5, max = 50)
    private String name;


    @NotNull(message = "NullCategoryDescriptionException: La descripción no debe ser nula")
    @NotBlank(message = "BlankCategoryDescriptionException: La descripción no debe estar llena de espacios")
    @Size(message = "WrongSizeCategoryDescriptionException: La descripción debe estar entre los 5 y los 90 caracteres", min = 5, max = 90)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDto that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
