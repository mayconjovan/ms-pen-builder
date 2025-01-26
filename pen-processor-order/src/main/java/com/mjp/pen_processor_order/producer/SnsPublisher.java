package com.mjp.pen_processor_order.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.ListTopicsResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Component
public class SnsPublisher {

    private final SnsClient snsClient;

    @Value("${aws.sns.topic-name}")
    private String topicName;

    public SnsPublisher(SnsClient snsClient) {
        this.snsClient = snsClient;
    }


    public void publishMessage(String message) {
        ListTopicsResponse topicsResponse = snsClient.listTopics();
        String topicArn = topicsResponse.topics().stream()
                .filter(topic -> topic.topicArn().endsWith(":" + topicName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"))
                .topicArn();

        PublishResponse published = snsClient.publish(PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build());

        System.out.println("SNS Message sent! Message ID: " + published.messageId());
    }
}
