package com.framepulse.content_onboarding_service.minio.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    public String uploadVideo(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            // Upload file to MinIO bucket
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return "http://" + endpoint + "/" + bucketName + "/" + fileName;
        } catch (MinioException e) {
            throw new RuntimeException("Error occurred while uploading video: " + e.getMessage());
        }
    }
}
