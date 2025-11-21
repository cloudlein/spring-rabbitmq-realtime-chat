package com.demo.chatApp.domain.service;

import com.demo.chatApp.adapter.value.AuthResult;
import com.demo.chatApp.domain.entity.User;

public interface AuthService {

        AuthResult login(String username, String rawPassword);
        void register(User user);
}
