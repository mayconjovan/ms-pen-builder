package com.mjp.pen_processor_order.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
public class SqsPublisher {

    private final SqsAsyncClient sqsClient;

    @Value("${aws.sqs.queue}")
    private String queueUrl;

    public SqsPublisher(SqsAsyncClient sqsClient) {
        this.sqsClient = sqsClient;
    }


    public void sendMessage(String messageBody) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();

        sqsClient.sendMessage(sendMessageRequest).whenComplete((response, exception) -> {
            if (exception != null) {
                System.err.println("Failed to send message: " + exception.getMessage());
            } else {
                System.out.println("Message sent! Message ID: " + response.messageId());
            }
        });
    }

}
