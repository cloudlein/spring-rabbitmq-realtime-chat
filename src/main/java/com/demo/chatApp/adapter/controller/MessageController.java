package com.demo.chatApp.adapter.controller;

import com.demo.chatApp.adapter.mapper.MessageMapper;
import com.demo.chatApp.common.api.ApiResponse;
import com.demo.chatApp.domain.entity.Message;
import com.demo.chatApp.domain.service.MessageService;
import com.demo.chatApp.dto.message.MessageRequestDto;
import com.demo.chatApp.dto.message.MessageResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/conversations")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @PostMapping
    public ApiResponse<MessageResponseDto> sendMessage
    (@RequestBody @Valid MessageRequestDto messageRequestDto) {
        Message message = messageMapper.toDomain(messageRequestDto);
        Message savedMessage = messageService.sendMessage(message);
    }
}
