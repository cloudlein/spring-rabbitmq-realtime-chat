package com.demo.chatApp.common.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tangani semua custom ApiException (misalnya ResourceNotFoundException, BadRequestException, dsb.)
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleApiException(ApiException ex) {
        log.warn("API exception occurred: {} (status: {})", ex.getMessage(), ex.getStatus());
        return ResponseEntity.status(ex.getStatus())
                .body(ApiResponse.<Void>builder()
                        .success(false)
                        .message(ex.getMessage())
                        .build());
    }

    // Tangani error validasi (misalnya @Valid / @NotBlank gagal)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ApiError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ApiError.builder()
                        .code(ApiErrorCode.VALIDATION_ERROR.getCode())
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        log.warn("Validation failed: {}", errors);
        return ApiResponseFactory.error(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    // Fallback (catch all) — untuk error yang tidak terduga
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleUnexpectedErrors(Exception ex) {
        log.error("Unexpected error occurred", ex);

        ApiError error = ApiError.builder()
                .code(ApiErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message("Internal server error")
                .build();

        return ApiResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong", List.of(error));
    }


}
