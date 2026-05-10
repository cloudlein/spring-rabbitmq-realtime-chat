package com.demo.chatApp.dto.conversation;

import com.demo.chatApp.dto.auth.AuthUserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponseDto {

    private Long id;
    private Boolean isGroup;
    private String groupName;
    private Set<AuthUserDto> participants;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
