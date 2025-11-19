package com.demo.chatApp.common.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginationMeta {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;
    private boolean last;
}
