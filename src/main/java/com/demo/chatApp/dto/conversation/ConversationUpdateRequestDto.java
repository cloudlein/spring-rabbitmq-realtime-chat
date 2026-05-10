package com.demo.chatApp.dto.conversation;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationUpdateRequestDto {

    @Size(max = 100, message = "Group name must be less than 100 characters")
    private String groupName;

    private Set<Long> participantIds;

}
