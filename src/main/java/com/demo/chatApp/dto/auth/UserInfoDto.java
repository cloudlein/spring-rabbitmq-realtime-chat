package com.demo.chatApp.dto.auth;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserInfoDto {

    private Long id;
    private String username;
    private String role;

}
