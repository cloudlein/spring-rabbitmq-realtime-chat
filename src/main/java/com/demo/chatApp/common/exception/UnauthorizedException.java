package com.demo.chatApp.common.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException{

    public UnauthorizedException(String message, HttpStatus status) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
