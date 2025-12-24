package org.example.expert.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.profile.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/users/{userId}/profile")
    public ResponseEntity<String> uploadProfileImage(
        @PathVariable long userId,
        @RequestParam MultipartFile image
    ) {
        String imageUrl = profileService.uploadProfileImage(userId, image);
        return ResponseEntity.ok().body(imageUrl);
    }
}
