package com.codecool.backend.fileStorage.aws;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "aws.s3.buckets")
public class S3Buckets {
    private String aprozar;
    private String user;


}
