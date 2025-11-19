package com.demo.chatApp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateRequestDto {

    private String name;
    private String username;
    private String password;
    private Boolean isActive;

}
