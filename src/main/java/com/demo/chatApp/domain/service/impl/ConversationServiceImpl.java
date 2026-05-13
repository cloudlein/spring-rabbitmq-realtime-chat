package com.demo.chatApp.domain.service.impl;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.repository.ConversationRepository;
import com.demo.chatApp.domain.repository.UserRepository;
import com.demo.chatApp.domain.service.BaseService;
import com.demo.chatApp.domain.service.ConversationService;
import com.demo.chatApp.dto.conversation.ConversationCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl extends BaseService implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Override
    public void createConversation(ConversationCreateRequestDto requestDto) {

        if (!requestDto.getIsGroup()) {
            if(requestDto.getParticipantIds().size() != 2) {
                throw new IllegalArgumentException("Private chat must have exactly 2 participants");
            }

            var iterator = requestDto.getParticipantIds().iterator();
            var userId1 = iterator.next();
            var userId2 = iterator.next();

            var existingConversation = conversationRepository.findPrivateConversation(userId1, userId2).orElse(null);
            if (existingConversation != null) {
                throw new RuntimeException("Private conversation already exists between these users");
            }
        }

        Set<User> users = new HashSet<>();

        for (Long userId : requestDto.getParticipantIds()) {
            User user = orNotFound(userRepository.findById(userId), "User not found");

            users.add(user);
        }

        Conversation conversation = Conversation.builder()
                .isGroup(requestDto.getIsGroup())
                .groupName(requestDto.getGroupName())
                .participants(users)
                .build();

        conversationRepository.save(conversation);
    }

    @Override
    public void removeConversation(Long id) {

    }
}
