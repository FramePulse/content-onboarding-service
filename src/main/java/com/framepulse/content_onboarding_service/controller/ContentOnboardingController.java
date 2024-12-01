package com.framepulse.content_onboarding_service.controller;

import com.framepulse.content_onboarding_service.minio.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/content-onboarding")
public class ContentOnboardingController {

    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadContent(@RequestParam("file")MultipartFile file){
        try {
            String fileUrl = minioService.uploadVideo(file);
            return ResponseEntity.status(200).body("File uploaded successfully: "+fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: "+e.getMessage());
        }
    }
}
