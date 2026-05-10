package com.demo.chatApp.domain.repository;

import com.demo.chatApp.domain.entity.Message;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    Message save(Message message);
    Optional<Message> findById(Long id);
    List<Message> findAll();
    void deleteById(Long id);
    List<Message> findByConversationId(Long userId, Pageable pageable);

}
