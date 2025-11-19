package com.demo.chatApp.adapter.mapper;

import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.dto.UserRequestDto;
import com.demo.chatApp.dto.UserResponseDto;
import com.demo.chatApp.dto.UserUpdateRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // response
    UserResponseDto toResponse(User user);

    // request
    User toDomain(UserRequestDto request);

    void updateUserFromDto(UserUpdateRequestDto request, @MappingTarget User user);

}
