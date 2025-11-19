package com.demo.chatApp.domain.service;

import com.demo.chatApp.domain.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {

    void create(User user);
    User getById(Long id);
    void update(Long id , User user);
    Page<User> findAll(int page, int size);
    void delete(Long id, User user);
}
