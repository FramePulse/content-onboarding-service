package com.framepulse.content_onboarding_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContentOnboardingDto {

    private String id;

    private String contentId;

    private String status;

    private String title;

    private String description;

    private String tags;

    private String storageUrl;

    private String createdBy;

    private String createdOn;
}
