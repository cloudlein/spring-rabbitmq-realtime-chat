package com.demo.chatApp.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Boolean isActive;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
