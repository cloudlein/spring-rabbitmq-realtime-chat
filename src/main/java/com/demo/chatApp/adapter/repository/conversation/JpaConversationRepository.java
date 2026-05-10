package com.demo.chatApp.adapter.repository.conversation;

import com.demo.chatApp.domain.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaConversationRepository extends JpaRepository<Conversation, Long>, JpaSpecificationExecutor<Conversation> {
    Optional<Conversation> findById(Long id);

    Page<Conversation> findAllConversationByUserId(Long userId, Pageable pageable);
}
