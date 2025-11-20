package com.demo.chatApp.adapter.mapper;

import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.dto.auth.LoginResponseDto;
import com.demo.chatApp.dto.auth.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "accessToken", source = "token")
    LoginResponseDto toLoginResponse(String token, User user);

    UserInfoDto toUserInfoDto(User user);

}
