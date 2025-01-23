package com.mjp.pen_processor_order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;


import java.net.URI;

@Configuration
public class SnsConfig {

    @Value("${sns.url}")
    private String url;
    @Value("${sns.credential.access_key_id}")
    private String accessKey;
    @Value("${sns.credential.secret_access_key}")
    private String secretKey;


    @Bean
    public SnsAsyncClient snsAsyncClient() {
        return SnsAsyncClient.builder()
                .endpointOverride(URI.create(url))
                .region(Region.SA_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .build();
    }
}
