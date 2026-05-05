package com.demo.chatApp.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {

    @NotNull(message = "Conversation ID is required")
    private Long conversationId;

    @NotBlank(message = "Message content cannot be empty")
    @Size(max = 2000, message = "Message content is too long")
    private String content;

}
