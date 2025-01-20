package com.mjp.pen_processor_order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;


import java.net.URI;

@Configuration
public class SnsConfig {

    @Bean
    public SnsAsyncClient snsAsyncClient() {
        return SnsAsyncClient.builder()
                .endpointOverride(URI.create("http://localhost:4566")) // LocalStack endpoint
                .region(Region.SA_EAST_1) // Região simulada
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("fakeAccessKey", "fakeSecretKey") // Credenciais fake
                ))
                .build();
    }
}
