package com.demo.chatApp.common.api;

import lombok.Builder;

@Builder
public record ApiError(
        String code,
        String field,
        String message
) {
}
