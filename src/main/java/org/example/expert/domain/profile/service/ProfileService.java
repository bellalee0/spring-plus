package org.example.expert.domain.profile.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.profile.entity.Profile;
import org.example.expert.domain.profile.repository.ProfileRepository;
import org.example.expert.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final S3Service s3Service;
    private final ProfileRepository profileRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    public String getProfileImage(AuthUser authUser, Long userId) {

        Profile profile;

        if (userId != null) {
            profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new InvalidRequestException("프로필 이미지가 존재하지 않습니다."));
        } else {
            profile = profileRepository.findByUserId(authUser.getId())
                .orElseThrow(() -> new InvalidRequestException("프로필 이미지가 존재하지 않습니다."));
        }

        return profile.getImageUrl();
    }

    @Transactional
    public void deleteProfileImage(AuthUser authUser) {

        Profile profile = profileRepository.findByUserId(authUser.getId())
            .orElseThrow(() -> new InvalidRequestException("프로필 이미지가 존재하지 않습니다."));

        profile.delete();
    }

    @Transactional
    public String updateProfileImage(AuthUser authUser, MultipartFile image) {

        User user = User.fromAuthUser(authUser);

        Profile oldProfile = profileRepository.findByUserId(authUser.getId())
            .orElseThrow(() -> new InvalidRequestException("프로필 이미지가 존재하지 않습니다."));
        oldProfile.delete();

        String key = UUID.randomUUID().toString();
        String fileName = user.getEmail() + "/" + key + image.getOriginalFilename();

        String imageUrl = s3Service.upload(image, fileName);

        Profile newProfile = new Profile(user, imageUrl);
        profileRepository.save(newProfile);

        return imageUrl;
    }
}
