package com.framepulse.content_onboarding_service.service;

import com.framepulse.common.service.AbstractCassandraService;
import com.framepulse.content_onboarding_service.entity.ContentOnboarding;
import com.framepulse.content_onboarding_service.minio.service.MinioService;
import com.framepulse.content_onboarding_service.repository.ContentOnboardingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ContentOnboardingService extends AbstractCassandraService<ContentOnboarding, String, ContentOnboardingRepository> {

    @Autowired
    private MinioService minioService;

    public ContentOnboarding startOnboarding(ContentOnboarding contentOnboarding) {
        if (contentOnboarding == null) {
            contentOnboarding = new ContentOnboarding();
        }
        contentOnboarding.setId(UUID.randomUUID().toString());
        contentOnboarding.setStatus("NEW");
        contentOnboarding = super.save(contentOnboarding);

        // TODO : update content by calling content service
        updateContent();

        return contentOnboarding;
    }

    public ContentOnboarding uploadContentFile(
            String onboardingId, MultipartFile file) throws Exception {
        Optional<ContentOnboarding> contentOnboarding = super.findById(onboardingId);
        if (contentOnboarding.isPresent()) {

            //upload file
            String fileUrl = minioService.uploadVideo(onboardingId, file);

            // TODO : update file url in content by calling content service
            updateContent();

            ContentOnboarding _contentOnboarding = contentOnboarding.get();
            _contentOnboarding.setStatus("UPLOADED");
            super.update(_contentOnboarding);

            pushToIncomingKafkaTopic(_contentOnboarding);

            return _contentOnboarding;
        }
        return null;
    }

    @Autowired
    private KafkaProducer kafkaProducer;

    private void pushToIncomingKafkaTopic(ContentOnboarding contentOnboarding) {
        kafkaProducer.sendContentOnboarding(contentOnboarding);
    }

    private void updateContent() {

    }
}
