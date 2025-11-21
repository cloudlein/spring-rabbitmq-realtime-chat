package com.demo.chatApp.domain.service.impl;

import com.demo.chatApp.adapter.value.AuthResult;
import com.demo.chatApp.common.exception.ResourceNotFoundException;
import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.enums.UserRole;
import com.demo.chatApp.domain.service.AuthService;
import com.demo.chatApp.domain.service.BaseService;
import com.demo.chatApp.domain.service.UserService;
import com.demo.chatApp.security.JwtProvider;
import com.demo.chatApp.security.JwtUserDetailService;
import com.demo.chatApp.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUserDetailService jwtUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Transactional
    @Override
    public AuthResult login(String username, String rawPassword) {

        UserPrincipal principal = (UserPrincipal) jwtUserDetailService.loadUserByUsername(username);

        if (!passwordEncoder.matches(rawPassword, principal.getPassword())) {
            throw new ResourceNotFoundException("Invalid username or password");
        }

        return AuthResult.builder()
                .user(principal.getUser())
                .accessToken(jwtProvider.generateToken(principal))
                .build();
    }

    @Transactional
    @Override
    public void register(User user) {
        if (userService.existByUsername(user.getUsername())) {
            throw new DataIntegrityViolationException("User already exist");
        }

        user.setIsActive(true);
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.create(user);
    }


}
