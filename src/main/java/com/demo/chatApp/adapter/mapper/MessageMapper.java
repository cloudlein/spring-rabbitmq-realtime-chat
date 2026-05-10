package com.demo.chatApp.adapter.mapper;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.entity.Message;
import com.demo.chatApp.dto.message.MessageRequestDto;
import com.demo.chatApp.dto.message.MessageResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "conversation.id", target = "conversationId")
    @Mapping(source = "sender", target = "sender")
    MessageResponseDto toDto(Message message);


    @Mapping(target = "conversation", source = "conversationId")
    @Mapping(target = "sender", ignore = true)
    Message toDomain(MessageRequestDto messageRequestDto);


    default Conversation mapIdToConversation(Long id) {
        if (id == null) return null;
        Conversation conversation = new Conversation();
        conversation.setId(id);
        return conversation;
    }
}