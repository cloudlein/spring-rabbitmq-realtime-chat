package com.demo.chatApp.adapter.mapper;

import com.demo.chatApp.domain.entity.Conversation;
import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.dto.conversation.ConversationCreateRequestDto;
import com.demo.chatApp.dto.conversation.ConversationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ConversationMapper {

    ConversationResponseDto toConversationResponseDto(Conversation conversation);

    @Mapping(target = "participants", source = "participantIds")
    Conversation toDomain(ConversationCreateRequestDto conversationCreateRequestDto);

    default User mapIdToUser(Long id) {
        if (id == null) return null;
        User user = new User();
        user.setId(id);
        return user;
    }
}
