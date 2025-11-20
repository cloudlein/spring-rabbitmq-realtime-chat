package com.demo.chatApp.adapter.controller;

import com.demo.chatApp.adapter.mapper.AuthMapper;
import com.demo.chatApp.adapter.value.AuthResult;
import com.demo.chatApp.common.api.ApiResponse;
import com.demo.chatApp.common.api.ApiResponseFactory;
import com.demo.chatApp.domain.service.AuthService;
import com.demo.chatApp.dto.auth.LoginRequestDto;
import com.demo.chatApp.dto.auth.LoginResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthMapper authMapper;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@RequestBody @Valid LoginRequestDto request) {
        AuthResult result = authService.login(request.getUsername(), request.getPassword());

        LoginResponseDto response = authMapper.toLoginResponse(
                result.getAccessToken(),
                result.getUser()
        );
        return ApiResponseFactory.success("Login successfully", response);
    }

}
