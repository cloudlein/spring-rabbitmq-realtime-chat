package com.demo.chatApp.common.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class ApiResponseFactory {

    private ApiResponseFactory() {}

    private static String getCurrentPath() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(attrs -> ((ServletRequestAttributes) attrs).getRequest().getRequestURI())
                .orElse("N/A");
    }

    // Success
    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return success(message, data, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data, HttpStatus status) {
        Meta meta = Meta.of(status.value(), getCurrentPath());
        return ResponseEntity.status(status)
                .body(ApiResponse.success(message,data,meta));
    }

    public static <T> ResponseEntity<ApiResponse<Void>> success(String message) {
        HttpStatus status = HttpStatus.OK;
        Meta meta = Meta.of(status.value(), getCurrentPath());
        return ResponseEntity.status(status)
                .body(
                        ApiResponse.<Void>builder()
                                .success(true)
                                .message(message)
                                .meta(meta)
                                .build()
                );
    }

    // Create
    public static <T> ResponseEntity<ApiResponse<T>>created(String message, T data) {
        HttpStatus status = HttpStatus.OK;
        Meta meta = Meta.of(status.value(), getCurrentPath());
        return ResponseEntity.status(status)
                .body(ApiResponse.success(message, data, meta));
    }
}
