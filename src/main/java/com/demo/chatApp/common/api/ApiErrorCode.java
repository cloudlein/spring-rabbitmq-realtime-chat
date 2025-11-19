package com.demo.chatApp.common.api;

import lombok.Getter;

@Getter
public enum ApiErrorCode {
    VALIDATION_ERROR("VALIDATION_ERROR"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    UNAUTHORIZED("UNAUTHORIZED"),
    FORBIDDEN("FORBIDDEN");

    private final String code;

    ApiErrorCode(String code) {
        this.code = code;
    }
}
