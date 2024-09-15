package com.kevin.emazon.application.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
public class ListToPageConversor {

    private ListToPageConversor(){}
    public static <D> Page<D> convertListIntoPage(List<D> domainModelList, Integer pageNumber, Integer pageSize) {
        return new PageImpl<>(domainModelList, PageRequest.of(pageNumber, pageSize), domainModelList.size());
    }
}