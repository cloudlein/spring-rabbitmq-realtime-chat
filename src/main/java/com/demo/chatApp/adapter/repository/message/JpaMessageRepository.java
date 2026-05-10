package com.demo.chatApp.adapter.repository.message;

import com.demo.chatApp.domain.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JpaMessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

    List<Message>findByConversationId(Long user, Pageable pageable);

}
