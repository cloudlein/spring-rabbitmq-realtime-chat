package com.demo.chatApp.domain.repository;

import com.demo.chatApp.domain.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository {

    Conversation save(Conversation conversation);

    Optional<Conversation> findById(long id);
    Page<Conversation> findAll(Pageable pageable);
    Page<Conversation> findAllConversationByUserId(Long userId, Pageable pageable);


    Optional<Conversation> findPrivateConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
}
