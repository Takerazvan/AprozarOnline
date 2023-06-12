package com.codecool.backend.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Value("eu-central-1")
    private String awsRegion;

    @Bean
    public S3Client s3Client() {
        S3Client client = S3Client.builder()
                .region(Region.EU_CENTRAL_1)
                .build();
        return client;
    }
}
