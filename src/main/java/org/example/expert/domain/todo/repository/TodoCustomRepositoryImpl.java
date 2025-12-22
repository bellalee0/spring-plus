package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo result = queryFactory
                        .selectFrom(todo)
                        .leftJoin(todo.user,user).fetchJoin()
                        .where(todo.id.eq(todoId))
                        .fetchOne();

        return Optional.ofNullable(result);
    }

    @Override
    public Page<TodoSearchResponse> findTodoByConditionsPage(
        String title, String nickname, LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable
    ) {

        BooleanBuilder builder = new BooleanBuilder();

        if (title != null && !title.isBlank()) {
            builder.and(todo.title.contains(title));
        }

        if (nickname != null && !nickname.isBlank()) {
            builder.and(user.nickname.contains(nickname));
        }

        if (startDateTime != null) {
            builder.and(todo.createdAt.after(startDateTime));
        }

        if (endDateTime != null) {
            builder.and(todo.createdAt.before(endDateTime));
        }

        List<TodoSearchResponse> responseList = queryFactory
            .select(Projections.constructor(TodoSearchResponse.class,
                    todo.title,
                    ExpressionUtils.as(
                        JPAExpressions.select(manager.count())
                            .from(manager)
                            .where(manager.todo.eq(todo)),
                        "managers"
                    ),
                    ExpressionUtils.as(
                        JPAExpressions.select(comment.count())
                            .from(comment)
                            .where(comment.todo.eq(todo)),
                        "comments"
                    )
                ))
            .from(todo)
            .where(builder)
            .orderBy(todo.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(todo.count())
            .from(todo)
            .where(builder)
            .fetchOne();

        if (total == null) { total = 0L; }

        return new PageImpl<>(responseList, pageable, total);
    }
}
