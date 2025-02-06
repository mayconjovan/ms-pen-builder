package com.mjp.factory.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory.adapters.outbound.sql.jpa.repository.JpaPenRepositoryImpl;
import com.mjp.factory.application.service.PenUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public PenUseCaseImpl penUseCaseImpl(JpaPenRepositoryImpl penRepository, ObjectMapper objectMapper) {
        return new PenUseCaseImpl(penRepository, objectMapper);
    }

}
