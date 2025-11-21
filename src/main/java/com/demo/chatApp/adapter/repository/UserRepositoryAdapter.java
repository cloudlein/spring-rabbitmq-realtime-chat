package com.demo.chatApp.adapter.repository;

import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository userRepo;

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(User user) {
         userRepo.delete(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return  userRepo.findByUsername(username);
    }

    @Override
    public boolean checkUserExists(String username) {
        return userRepo.existByUsername(username);
    }


}
