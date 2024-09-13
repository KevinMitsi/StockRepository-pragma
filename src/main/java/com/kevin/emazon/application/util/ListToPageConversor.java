package com.kevin.emazon.application.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
public class ListToPageConversor {

    public static final int PAGE_NUMBER = 0;
    public static final int PAGE_SIZE = 10;

    private ListToPageConversor(){}
    public static <D> Page<D> convertListIntoPage(List<D> domainModelList) {
        return new PageImpl<>(domainModelList, PageRequest.of(PAGE_NUMBER, PAGE_SIZE), domainModelList.size());
    }
}