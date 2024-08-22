package com.kevin.emazon.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Brand {
    private Long id;
    private String name;
    private String description;

    public Brand() {}

    public Brand(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
