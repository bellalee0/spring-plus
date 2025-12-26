package org.example.expert.domain.profile.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.profile.entity.Profile;
import org.example.expert.domain.profile.repository.ProfileRepository;
import org.example.expert.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final S3Service s3Service;
    private final ProfileRepository profileRepository;

    public String uploadProfileImage(AuthUser authUser, MultipartFile image) {

        User user = User.fromAuthUser(authUser);

        if (profileRepository.existsByUserId(user.getId())) {
            throw new InvalidRequestException("프로필 이미지가 존재합니다.");
        }

        String key = UUID.randomUUID().toString();
        String fileName = user.getEmail() + "/" + key + image.getOriginalFilename();

        String imageUrl = s3Service.upload(image, fileName);

        Profile profile = new Profile(user, imageUrl);
        profileRepository.save(profile);

        return imageUrl;
    }
}
