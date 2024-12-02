package com.framepulse.content_onboarding_service.mapper;

import com.framepulse.common.mapper.AbstractMapper;
import com.framepulse.content_onboarding_service.dto.ContentOnboardingDto;
import com.framepulse.content_onboarding_service.entity.ContentOnboarding;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContentOnboardingMapper extends AbstractMapper<ContentOnboarding, ContentOnboardingDto> {}
