package com.kevin.emazon.infraestructure.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCreator {
    private PageableCreator(){}

    public static Pageable createPageable(String order){
        Sort sort = Sort.by("name").ascending();
        if ("desc".equalsIgnoreCase(order)) {
            sort = sort.descending();
        }
        return PageRequest.of(0, 10, sort);
    }
}
