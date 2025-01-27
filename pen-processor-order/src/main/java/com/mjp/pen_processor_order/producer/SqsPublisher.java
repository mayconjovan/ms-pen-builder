package com.mjp.pen_processor_order.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Component
public class SqsPublisher {

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue}")
    private String queueUrl;

    public SqsPublisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }


    public void sendMessage(String messageBody) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build();

        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMessageRequest);

        System.out.println("Message sent! Message ID: " + sendMessageResponse.messageId());
    }
}
