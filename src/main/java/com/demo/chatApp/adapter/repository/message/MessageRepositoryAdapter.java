package com.demo.chatApp.adapter.repository.message;

import com.demo.chatApp.domain.entity.Message;
import com.demo.chatApp.domain.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryAdapter implements MessageRepository {

    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Optional<Message> findById(long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> findByConversationId(long userId,  Pageable pageable) {
        return messageRepository.findByConversationId(userId, pageable);
    }
}
