package org.example.expert.domain.profile.repository;

import org.example.expert.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    boolean existsByUserId(long userId);
}
