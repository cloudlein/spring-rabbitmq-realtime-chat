package com.demo.chatApp.adapter.seeder;

import com.demo.chatApp.adapter.repository.JpaUserRepository;
import com.demo.chatApp.domain.entity.User;
import com.demo.chatApp.domain.enums.UserRole;
import com.demo.chatApp.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(9)
@RequiredArgsConstructor
@Slf4j
public class UserSeeder implements ApplicationRunner {

    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(jpaUserRepository.count() > 0) {
            log.info("[UserSeeder] data already seeded - skip");
            return;
        }

        log.info("[UserSeeder] starting..");

        User user = User.builder()
                .name("admin")
                .username("admin")
                .password(passwordEncoder.encode("asdf"))
                .isActive(true)
                .role(UserRole.ADMIN)
                .build();

        jpaUserRepository.save(user);
    }
}
