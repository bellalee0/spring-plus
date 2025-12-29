package org.example.expert.domain.user.repository;

import static org.example.expert.domain.user.entity.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.user.dto.response.UserSearchResponse;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserSearchResponse> findAllByNickname(String nickname) {
        return queryFactory
            .select(Projections.constructor(UserSearchResponse.class,
                user.id, user.nickname))
            .from(user)
            .where(user.nickname.eq(nickname))
            .fetch();
    }
}
