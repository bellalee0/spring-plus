package org.example.expert.domain.user.repository;

import java.util.List;
import org.example.expert.domain.user.dto.response.UserSearchResponse;
import org.example.expert.domain.user.entity.User;

public interface UserCustomRepository {
    List<User> findAllByNickname(String nickname);
}
