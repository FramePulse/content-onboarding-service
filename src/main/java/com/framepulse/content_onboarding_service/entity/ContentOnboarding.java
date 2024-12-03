package com.framepulse.content_onboarding_service.entity;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@Table("content_onboarding")
public class ContentOnboarding {

    @PrimaryKey
    private String id;

    private String contentId;

    private String status;
}
