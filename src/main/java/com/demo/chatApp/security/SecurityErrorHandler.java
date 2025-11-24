package com.demo.chatApp.security;

import com.demo.chatApp.common.api.ApiError;
import com.demo.chatApp.common.api.ApiErrorCode;
import com.demo.chatApp.common.api.ApiResponse;
import com.demo.chatApp.common.api.ApiResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityErrorHandler {

    private final ObjectMapper objectMapper;

    public void handleAccessDenied(HttpServletResponse response) throws IOException {
        ApiError error = ApiError.builder()
                .code(ApiErrorCode.FORBIDDEN.getCode())
                .message("You do not have permission to access this resource.")
                .build();

        ApiResponse<Object> body = ApiResponseFactory
                .error(HttpStatus.FORBIDDEN, "Forbidden", List.of(error))
                .getBody();

        write(response, HttpStatus.FORBIDDEN, body);
    }

    public void handleUnauthorized(HttpServletResponse response) throws IOException {
        ApiError error = ApiError.builder()
                .code(ApiErrorCode.UNAUTHORIZED.getCode())
                .message("Authentication is required.")
                .build();


        ApiResponse<Object> body = ApiResponseFactory
                .error(HttpStatus.UNAUTHORIZED, "Unauthorized", List.of(error))
                .getBody();

        write(response, HttpStatus.UNAUTHORIZED, body);
    }

    public void handleMethodAccessDenied(HttpServletResponse response) throws IOException {
        ApiError error = ApiError.builder()
                .code(ApiErrorCode.FORBIDDEN.getCode())
                .message("You do not have permission to access this resource.")
                .build();

        ApiResponse<Object> body = ApiResponseFactory
                .error(HttpStatus.FORBIDDEN, "Forbidden", List.of(error))
                .getBody();

        write(response, HttpStatus.FORBIDDEN, body);
    }


    private void write(HttpServletResponse response, HttpStatus status, ApiResponse<?> body) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
