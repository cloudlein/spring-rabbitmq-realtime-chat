package com.demo.chatApp.domain.repository;

import com.demo.chatApp.domain.entity.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    Message save(Message message);
    Optional<Message> findById(long id);
    List<Message> findAll();
    void deleteById(long id);
    List<Message> findByConversationId(long userId);

}
