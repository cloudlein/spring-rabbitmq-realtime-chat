package com.demo.chatApp.domain.service;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.dto.conversation.ConversationCreateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConversationService {

     Conversation createConversation(Conversation conversation);


    void removeConversation(Long userId, Long conversationId);
    Page<Conversation> getAllConversationByUserId(Long userId, int page , int size);
}
