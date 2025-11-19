package com.demo.chatApp.common.api;

import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Getter
@Builder
public class Meta {

    private  int status;
    private String path;
    private Instant timestamp;
    private String traceId;
    private PaginationMeta pagination;

    public static Meta of(int status, String path) {
        String traceId = Optional.ofNullable(MDC.get("traceId"))
                .orElse(UUID.randomUUID().toString());

        return Meta.builder()
                .status(status)
                .path(path)
                .timestamp(Instant.now())
                .traceId(traceId)
                .build();
    }

    public static Meta withPagination(int status, String path, PaginationMeta paginationMeta) {
        return Meta.builder()
                .status(status)
                .path(path)
                .timestamp(Instant.now())
                .traceId(UUID.randomUUID().toString())
                .pagination(paginationMeta)
                .build();
    }

}
