package com.demo.chatApp.adapter.repository.user;

import com.demo.chatApp.domain.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class UserSpesification {

    // Filter by Full Name (Partial match)
    public static Specification<User> hasName(String name) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(name)) return null;
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
    // Filter by Username (Partial match for search)
    public static Specification<User> hasUsername(String username) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(username)) return null;
            return cb.like(cb.lower(root.get("username")), "%" + username.toLowerCase() + "%");
        };
    }
    // Filter by Active status
    public static Specification<User> isActive(Boolean active) {
        return (root, query, cb) -> {
            if (active == null) return null;
            return cb.equal(root.get("isActive"), active);
        };
    }

}
