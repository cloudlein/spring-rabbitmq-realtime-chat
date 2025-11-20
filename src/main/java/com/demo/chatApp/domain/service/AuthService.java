package com.demo.chatApp.domain.service;

import com.demo.chatApp.adapter.value.AuthResult;
import com.demo.chatApp.dto.auth.LoginResponseDto;

public interface AuthService {

        AuthResult login(String username, String rawPassword);

}
