package com.demo.chatApp.adapter.mapper;

import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.dto.auth.AuthUserDto;
import com.demo.chatApp.dto.user.UserCreateRequestDto;
import com.demo.chatApp.dto.user.UserResponseDto;
import com.demo.chatApp.dto.user.UserUpdateRequestDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    AuthUserDto toAuthUserDto(User user);

    UserResponseDto toUserResponseDto(User user);

    @Mapping(target = "role", constant = "USER")
    User toEntity(UserCreateRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDto(UserUpdateRequestDto dto, @MappingTarget User entity);


}
