package com.demo.chatApp.domain.service;

import com.demo.chatApp.domain.entity.Message;
import com.demo.chatApp.dto.message.MessageRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {

    void sendMessage(MessageRequestDto requestDto);

    Page<Message> findByConversationId(Long userId, Long conversationId,  int page, int size);

}
