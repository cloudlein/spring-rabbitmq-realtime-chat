package com.demo.chatApp.adapter.repository.conversation;

import com.demo.chatApp.domain.entity.Conversation;
import org.springframework.data.jpa.domain.Specification;

public class ConversationSpecification {

    // Filter by group name (Like)
    public static Specification<Conversation> hasGroupName(String groupName) {
        return (root, query, cb) -> {
            if (groupName == null || groupName.isEmpty()) return null;
            return cb.like(cb.lower(root.get("groupName")), "%" + groupName.toLowerCase() + "%");
        };
    }

    // Filter by group status
    public static Specification<Conversation> hasGroupId(Boolean isGroup) {
        return (root, query, cb) -> {
            if (isGroup == null) return null;
            return cb.equal(root.get("isGroup"), isGroup);
        };
    }

    // Filter by User ID (Search within the Participants list)
    public static Specification<Conversation> hasParticipant(Long userId) {
        return (root, query, cb) -> {
            if (userId == null) return null;

            // Perform a join on the participants table
            return cb.equal(root.join("participants").get("id"), userId);
        };
    }
}
