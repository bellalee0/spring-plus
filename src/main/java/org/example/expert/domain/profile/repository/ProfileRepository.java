package org.example.expert.domain.profile.repository;

import java.util.Optional;
import org.example.expert.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    boolean existsByUserId(long userId);

    Optional<Profile> findByUserId(Long id);
}
