package com.demo.chatApp.domain.repository;

import com.demo.chatApp.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {


    Optional<User> findById(Long id);

    User save(User user);

    void deleteUser(User user);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);

}
