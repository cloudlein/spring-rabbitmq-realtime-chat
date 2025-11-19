package com.demo.chatApp.common.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException{

    protected BadRequestException(String message, HttpStatus status) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
