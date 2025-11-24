package com.demo.chatApp.domain.service.impl;

import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.repository.UserRepository;
import com.demo.chatApp.domain.service.BaseService;
import com.demo.chatApp.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return orNotFound(userRepository.findById(id), "User not found");
    }

    @Transactional
    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User existing = orNotFound(userRepository.findById(id), "User not found" );
        userRepository.deleteUser(existing);
    }

    @Override
    public User getByUsername(String username) {
        return orNotFound(userRepository.findByUsername(username), "Username not found");
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.checkUserExists(username);
    }

}
