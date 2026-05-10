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

    private final JpaMessageRepository jpaMessageRepository;

    @Override
    public Message save(Message message) {
        return jpaMessageRepository.save(message);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return jpaMessageRepository.findById(id);
    }

    @Override
    public List<Message> findAll() {
        return jpaMessageRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaMessageRepository.deleteById(id);
    }

    @Override
    public List<Message> findByConversationId(Long userId,  Pageable pageable) {
        return jpaMessageRepository.findByConversationId(userId, pageable);
    }
}
