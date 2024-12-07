package com.framepulse.content_onboarding_service.service;

import com.framepulse.content_onboarding_service.entity.ContentOnboarding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,ContentOnboarding> kafkaTemplate;

    public void sendContentOnboarding(ContentOnboarding contentOnboarding){
        this.kafkaTemplate.send("incoming", contentOnboarding);
    }
}
