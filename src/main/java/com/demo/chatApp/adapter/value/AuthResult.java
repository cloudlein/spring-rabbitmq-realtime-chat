package com.demo.chatApp.adapter.value;

import com.demo.chatApp.domain.entity.User;
import lombok.*;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class AuthResult {
        private User user;
        private String accessToken;
    }