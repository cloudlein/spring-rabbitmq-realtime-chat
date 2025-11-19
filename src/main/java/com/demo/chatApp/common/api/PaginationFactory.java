package com.demo.chatApp.common.api;

import org.springframework.data.domain.Page;

public class PaginationFactory {
    private PaginationFactory() {}

    public static <E> PaginationMeta from(Page<E> pageData) {
        return PaginationMeta.builder()
                .currentPage(pageData.getNumber())
                .size(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .last(pageData.isLast())
                .build();
    }
}
