package com.framepulse.content_onboarding_service.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Data
@Table("content_onboarding")
public class ContentOnboarding {

    @PrimaryKey
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
