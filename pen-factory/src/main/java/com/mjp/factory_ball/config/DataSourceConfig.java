package com.mjp.factory_ball.config;

import com.mjp.factory_ball.service.SecretsManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final SecretsManagerService secretsManagerService;


    public DataSourceConfig(SecretsManagerService secretsManagerService) {
        this.secretsManagerService = secretsManagerService;
    }

    @Bean
    public DataSource dataSource() {
        Map<String, String> secrets = secretsManagerService.getSecret("db-factory-credentials");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(secrets.get("url"));
        dataSource.setUsername(secrets.get("username"));
        dataSource.setPassword(secrets.get("password"));
        return dataSource;
    }
}
