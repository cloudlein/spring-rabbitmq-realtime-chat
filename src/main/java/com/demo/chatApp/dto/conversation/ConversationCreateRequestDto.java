package com.demo.chatApp.dto.conversation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationCreateRequestDto {

    @NotNull(message = "isGroup status is required")
    private Boolean isGroup;

    @Size(max = 100, message = "Group name must be less than 100 characters")
    private String groupName;

    @NotEmpty(message = "Participant IDs cannot be empty")
    private Set<Long> participantIds;

}
