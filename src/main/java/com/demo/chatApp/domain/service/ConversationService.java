package com.demo.chatApp.domain.service;

import com.demo.chatApp.domain.entity.Conversation;

public interface ConversationService {

    void addConversation(Conversation conversation);
    void removeConversation(Long id);


}
