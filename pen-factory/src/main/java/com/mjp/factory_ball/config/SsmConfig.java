package com.mjp.factory_ball.config;

import com.mjp.factory_ball.service.SsmPropertySourceService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import software.amazon.awssdk.services.ssm.SsmClient;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SsmConfig {

    private final SsmClient ssmClient;
    private final ConfigurableEnvironment environment;

    public SsmConfig(SsmClient ssmClient, ConfigurableEnvironment environment) {
        this.ssmClient = ssmClient;
        this.environment = environment;
    }

    @PostConstruct
    public void addSsmPropertySource() {
        SsmPropertySourceService ssmPropertySourceService = new SsmPropertySourceService("ssmProperties", ssmClient);
        Map<String, String> propertiesFromSsm = ssmPropertySourceService.loadPropertiesFromSsm(
                "name",
                "port",
                "delay-millis",
                "queue-listener");

        Map<String, Object> properties = new HashMap<>(propertiesFromSsm);

        environment.getPropertySources().addFirst(new MapPropertySource("ssmProperties", properties));
    }
}

