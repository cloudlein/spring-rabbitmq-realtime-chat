package com.demo.chatApp.adapter.repository.conversation;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConversationRepositoryAdapter implements ConversationRepository {

    private final JpaConversationRepository conversationRepository;

    @Override
    public Conversation save(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    @Override
    public Optional<Conversation> findById(long id) {
        return conversationRepository.findById(id);
    }

    @Override
    public Page<Conversation> findAll(Pageable pageable) {
        return conversationRepository.findAll(pageable);
    }

    @Override
    public Page<Conversation> findAllConversationByUserId(Long userId, Pageable pageable) {
        return conversationRepository.findAllConversationByUserId(userId, pageable);
    }
}
