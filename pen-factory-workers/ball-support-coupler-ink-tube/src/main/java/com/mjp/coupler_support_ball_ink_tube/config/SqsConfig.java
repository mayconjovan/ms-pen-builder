package com.mjp.coupler_support_ball_ink_tube.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Bean
    public SqsAsyncClient sqsAsyncClient(){
        return SqsAsyncClient.builder()
                .endpointOverride(URI.create("http://localhost:4566"))
                .region(Region.SA_EAST_1)
                .build();
    }
}
