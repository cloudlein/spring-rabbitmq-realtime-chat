package com.demo.chatApp.adapter.repository.conversation;

import com.demo.chatApp.domain.entity.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaConversationRepository extends JpaRepository<Conversation, Long>, JpaSpecificationExecutor<Conversation> {
    Optional<Conversation> findById(Long id);

    Page<Conversation> findAllByParticipants_Id(Long userId, Pageable pageable);

    @Query("SELECT c FROM Conversation c JOIN c.participants p1 JOIN c.participants p2 " +
            "WHERE c.isGroup = false AND p1.id = :userId1 AND p2.id = :userId2")
    Optional<Conversation> findPrivateConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    void existsByIdAndParticipants_Id(Long conversationId, Long userId);
}
