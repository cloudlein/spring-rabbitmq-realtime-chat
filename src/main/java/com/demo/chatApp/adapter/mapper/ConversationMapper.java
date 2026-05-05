package com.demo.chatApp.adapter.mapper;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.dto.conversation.ConversationCreateRequestDto;
import com.demo.chatApp.dto.conversation.ConversationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

    ConversationResponseDto toConversationResponseDto(Conversation conversation);

    @Mapping(target = "participants", ignore = true)
    Conversation toDomain(ConversationCreateRequestDto conversationCreateRequestDto);

}
