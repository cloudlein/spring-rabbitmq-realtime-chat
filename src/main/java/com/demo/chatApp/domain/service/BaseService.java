package com.demo.chatApp.domain.service;

import com.demo.chatApp.common.exception.ResourceNotFoundException;

import java.util.Optional;

public abstract class BaseService {

    protected <T> T orNotFound(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new ResourceNotFoundException(message));
    }
}