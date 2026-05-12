package com.demo.chatApp.domain.service.impl;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.repository.ConversationRepository;
import com.demo.chatApp.domain.service.BaseService;
import com.demo.chatApp.domain.service.ConversationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl extends BaseService implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Transactional
    @Override
    public void addConversation(Conversation conversation) {

    }

    @Override
    public void removeConversation(Long id) {

    }
}
