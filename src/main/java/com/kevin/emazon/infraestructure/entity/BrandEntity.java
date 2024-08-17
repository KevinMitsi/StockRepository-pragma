package com.kevin.emazon.infraestructure.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30, message = "El nombre debe contener al menos 3 caracteres y no superar los 30")
    private String name;

    @NotBlank
    @Size(min = 10, max = 200, message = "El nombre debe contener al menos 10 caracteres y no superar los 200")
    private String description;

    @OneToMany(mappedBy = "brand")
    private Set<ItemEntity> items;

}
