package com.demo.chatApp.domain.service;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.dto.conversation.ConversationCreateRequestDto;

public interface ConversationService {

     void createConversation(ConversationCreateRequestDto requestDto);


    void removeConversation(Long id);


}
