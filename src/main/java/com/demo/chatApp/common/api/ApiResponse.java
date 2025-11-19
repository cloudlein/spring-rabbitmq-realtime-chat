package com.demo.chatApp.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T>{

    private Boolean success;
    private String message;
    private T data;
    private List<ApiError> errors;
    private Meta meta;

    public static <T> ApiResponse<T> success(String message, T data, Meta meta) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .meta(meta)
                .build();
    }

    public static <T> ApiResponse<T> failure(String message, List<ApiError> errors, Meta meta) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .errors(errors)
                .meta(meta)
                .build();
    }
}
