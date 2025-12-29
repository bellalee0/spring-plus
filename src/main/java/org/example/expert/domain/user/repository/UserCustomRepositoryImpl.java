package org.example.expert.domain.user.repository;

import static org.example.expert.domain.user.entity.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.user.entity.User;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<User> findAllByNickname(String nickname) {
        return queryFactory
            .selectFrom(user)
            .where(user.nickname.eq(nickname))
            .fetch();
    }
}
