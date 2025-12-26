package org.example.expert.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.profile.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<String> uploadProfileImage(
        @Auth AuthUser authUser,
        @RequestParam MultipartFile image
    ) {
        String imageUrl = profileService.uploadProfileImage(authUser, image);
        return ResponseEntity.ok().body(imageUrl);
    }

    @GetMapping
    public ResponseEntity<String> getProfileImage(
        @Auth AuthUser authUser,
        @RequestParam(required = false) Long userId
    ) {
        String imageUrl = profileService.getProfileImage(authUser, userId);
        return ResponseEntity.ok().body(imageUrl);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfileImage(
        @Auth AuthUser authUser
    ) {
        profileService.deleteProfileImage(authUser);
        return ResponseEntity.ok().build();
    }
}
