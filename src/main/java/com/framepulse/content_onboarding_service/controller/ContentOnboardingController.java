package com.framepulse.content_onboarding_service.controller;

import com.framepulse.content_onboarding_service.dto.ContentOnboardingDto;
import com.framepulse.content_onboarding_service.entity.ContentOnboarding;
import com.framepulse.content_onboarding_service.mapper.ContentOnboardingMapper;
import com.framepulse.content_onboarding_service.service.ContentOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/content-onboarding")
public class ContentOnboardingController {

    @Autowired
    private ContentOnboardingService contentOnboardingService;

    @Autowired
    private ContentOnboardingMapper contentOnboardingMapper;

    @PostMapping("/onboard")
    public ContentOnboardingDto startOnboarding(){
        ContentOnboarding contentOnboarding = contentOnboardingService.startOnboarding();
        return contentOnboardingMapper.entityToDto(contentOnboarding);
    }

    @PostMapping("/{onboardingId}/upload")
    public ResponseEntity<ContentOnboardingDto> uploadContent(
            @PathVariable String onboardingId,
            @RequestParam("file") MultipartFile file) {
        ContentOnboardingDto contentOnboardingDto = null;
            try {
                ContentOnboarding contentOnboarding = contentOnboardingService.uploadContentFile(onboardingId, file);
                contentOnboardingDto = contentOnboardingMapper.entityToDto(contentOnboarding);
            } catch (Exception e) {
                return ResponseEntity.status(500).body(null);
            }
            return ResponseEntity.status(200).body(contentOnboardingDto);
    }

    @GetMapping("/{onboardingId}")
    public Optional<ContentOnboarding> getOnboarding(@PathVariable String onboardingId) {
        return contentOnboardingService.findById(onboardingId);
    }
}
