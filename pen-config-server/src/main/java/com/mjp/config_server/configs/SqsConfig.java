package com.mjp.config_server.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "aws.sqs")
public class SqsConfig {

    private Map<String, String> queues;

    public Map<String, String> getQueues(){
        return queues;
    }

    public void setQueues(Map<String, String> queues) {
        this.queues = queues;
    }
}
