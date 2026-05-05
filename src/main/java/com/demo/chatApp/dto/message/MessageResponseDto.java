package com.demo.chatApp.dto.message;

import com.demo.chatApp.dto.auth.AuthUserDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {

    private Long id;
    private Long conversationId;
    private AuthUserDto sender;
    private String content;
    private LocalDateTime createdAt;

}
