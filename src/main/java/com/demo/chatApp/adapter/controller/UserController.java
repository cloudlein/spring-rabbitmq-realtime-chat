package com.demo.chatApp.adapter.controller;

import com.demo.chatApp.adapter.mapper.UserMapper;
import com.demo.chatApp.common.api.ApiResponse;
import com.demo.chatApp.common.api.ApiResponseFactory;
import com.demo.chatApp.common.api.PaginationFactory;
import com.demo.chatApp.common.api.PaginationMeta;
import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.service.UserService;
import com.demo.chatApp.dto.UserRequestDto;
import com.demo.chatApp.dto.UserResponseDto;
import com.demo.chatApp.dto.UserUpdateRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>>create(
            @RequestBody
            @Valid
            UserRequestDto request
    ){
        userService.create(userMapper.toDomain(request));
        return ApiResponseFactory.created("User created successfully");
    }

    @PatchMapping(
            path = "/{id}"
    )
    public ResponseEntity<ApiResponse<Void>>update(
            @RequestBody
            @Valid
            UserUpdateRequestDto request,
            @PathVariable Long id
    ){
        User existing = userService.getById(id);
        userMapper.updateUserFromDto(request, existing);
        userService.update(existing);
        return ApiResponseFactory.success("User updated successfully");
    }

    @DeleteMapping(
            path = "/{id}"
    )
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id
    ){
        userService.delete(id);
        return ApiResponseFactory.success("User deleted successfully");
    }

    @GetMapping(
            path = "/{id}"
    )
    public ResponseEntity<ApiResponse<UserResponseDto>>findById(
            @PathVariable Long id
    ){
        var user = userService.getById(id);
        var response = userMapper.toResponse(user);
        return ApiResponseFactory.success("User retrieved successfully", response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<User> users = userService.findAll(page,size);
        List<UserResponseDto> response = users.getContent()
                .stream()
                .map(userMapper::toResponse)
                .toList();
        PaginationMeta meta = PaginationFactory.from(users);

        return ApiResponseFactory.paginated("Users fetched successfully", response, meta);
    }
}
