package com.kevin.emazon.infraestructure.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCreator {

    public static final String SORT_BY = "name";
    public static final String SORTING_METHOD = "desc";

    private PageableCreator(){}

    public static Pageable createPageable(String order, Integer pageNumber, Integer pageSize){
        Sort sort = Sort.by(SORT_BY).ascending();
        if (SORTING_METHOD.equalsIgnoreCase(order)) {
            sort = sort.descending();
        }
        return PageRequest.of(pageNumber, pageSize, sort);
    }
}
