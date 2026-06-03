package com.demo.chatApp.adapter.controller;

import com.demo.chatApp.adapter.mapper.ConversationMapper;
import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.service.ConversationService;
import com.demo.chatApp.dto.conversation.ConversationCreateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.demo.chatApp.common.api.ApiResponse;
import com.demo.chatApp.common.api.ApiResponseFactory;
import com.demo.chatApp.dto.conversation.ConversationResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/conversations")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationMapper conversationMapper;


    @PostMapping
    public ResponseEntity<ApiResponse<ConversationResponseDto>> addConversation(
            @RequestBody @Valid
            ConversationCreateRequestDto requestDto
    ) {
        Conversation conversation = conversationMapper.toDomain(requestDto);
        Conversation savedConversation = conversationService.createConversation(conversation);
        ConversationResponseDto response = conversationMapper.toConversationResponseDto(savedConversation);
        return ApiResponseFactory.success("Conversation created successfully", response);
    }
}
