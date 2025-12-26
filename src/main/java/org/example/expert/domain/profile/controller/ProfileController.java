package org.example.expert.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.profile.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/users/profile")
    public ResponseEntity<String> uploadProfileImage(
        @Auth AuthUser authUser,
        @RequestParam MultipartFile image
    ) {
        String imageUrl = profileService.uploadProfileImage(authUser, image);
        return ResponseEntity.ok().body(imageUrl);
    }
}
