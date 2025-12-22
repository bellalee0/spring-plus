package org.example.expert.domain.todo.dto.response;

import lombok.Getter;

@Getter
public class TodoSearchResponse {

    private final String title;
    private final Long managers;
    private final Long comments;

    public TodoSearchResponse(String title, Long managers, Long comments) {
        this.title = title;
        this.managers = managers;
        this.comments = comments;
    }
}
