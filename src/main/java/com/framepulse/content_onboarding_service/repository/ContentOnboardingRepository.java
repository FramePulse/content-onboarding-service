package com.framepulse.content_onboarding_service.repository;

import com.framepulse.content_onboarding_service.entity.ContentOnboarding;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentOnboardingRepository extends CassandraRepository<ContentOnboarding, String> { }
