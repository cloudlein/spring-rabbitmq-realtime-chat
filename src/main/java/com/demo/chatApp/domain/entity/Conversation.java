package com.demo.chatApp.domain.entity;

import com.demo.chatApp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Table(name = "conversations", indexes = {
        @Index(name = "idx_conversation_is_group", columnList = "isGroup")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Conversation extends BaseEntity {

    private Boolean isGroup;
    
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "conversation_participants",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> participants = new HashSet<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages;

}
