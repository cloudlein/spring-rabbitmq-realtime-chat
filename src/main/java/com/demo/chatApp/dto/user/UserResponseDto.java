package com.demo.chatApp.dto.user;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {

    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
