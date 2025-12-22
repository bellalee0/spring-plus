package org.example.expert.domain.log.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long requestUserId;
    private Long targetUserId;
    private Long todoId;
    private boolean success;
    private String description;
    private LocalDateTime loggedDateTime;

    public Log(Long user, Long targetUser, Long todo, boolean success, String description) {
        this.requestUserId = user;
        this.todoId = targetUser;
        this.targetUserId = todo;
        this.success = success;
        this.description = description;
        this.loggedDateTime = LocalDateTime.now();
    }
}
