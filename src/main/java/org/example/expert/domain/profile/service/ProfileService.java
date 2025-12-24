package org.example.expert.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final S3Service s3Service;

    public String uploadProfileImage(long userId, MultipartFile image) {

        String fileName = userId + image.getOriginalFilename();
        String imageUrl = s3Service.upload(image, fileName);
        return imageUrl;
    }
}
