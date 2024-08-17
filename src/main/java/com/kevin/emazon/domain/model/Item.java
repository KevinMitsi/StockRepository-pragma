package com.kevin.emazon.domain.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private Long id;

    private String name;

    private Long stockQuantity;

    private Brand brand;

    private Set<Category> categories;

}
