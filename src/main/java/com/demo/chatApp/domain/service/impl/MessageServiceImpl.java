package com.demo.chatApp.domain.service.impl;

import com.demo.chatApp.adapter.mapper.MessageMapper;
import com.demo.chatApp.common.exception.UnauthorizedException;
import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.entity.Message;
import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.repository.ConversationRepository;
import com.demo.chatApp.domain.repository.MessageRepository;
import com.demo.chatApp.domain.repository.UserRepository;
import com.demo.chatApp.domain.service.BaseService;
import com.demo.chatApp.domain.service.MessageService;
import com.demo.chatApp.dto.message.MessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends BaseService implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final MessageMapper messageMapper;

    @Override
    public void sendMessage(MessageRequestDto requestDto) {
        User user = orNotFound(userRepository.findById(requestDto.getSenderId()), "User not found");

        Conversation conversation = orNotFound(conversationRepository.findById(requestDto.getConversationId()), "Conversation not found");

        boolean isParticipant = conversation.getParticipants().stream().anyMatch(
                participant -> participant.getId().equals(requestDto.getSenderId())
        );
        if (!isParticipant) {
            throw new UnauthorizedException("You are not participant on this current conversation", HttpStatus.UNAUTHORIZED);
        }

        Message message = messageMapper.toDomain(requestDto);
        message.setSender(user);
        message.setConversation(conversation);

        messageRepository.save(message);
    }

    @Override
    public Page<Message> findByConversationId(Long userId, Long conversationId, int page, int size) {

        Conversation conversation = orNotFound(conversationRepository.findById(conversationId), "Conversation not found");

        boolean isParticipant = conversation.getParticipants().stream().anyMatch(
                participant -> participant.getId().equals(userId)
        );

        if (!isParticipant) {
            throw new UnauthorizedException("You are not participant on this current conversation", HttpStatus.UNAUTHORIZED);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return messageRepository.findByConversationId(conversationId, pageable);
    }
}
