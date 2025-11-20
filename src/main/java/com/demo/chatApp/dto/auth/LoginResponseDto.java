package com.demo.chatApp.dto.auth;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private String accessToken;
    private UserInfoDto user;

}
