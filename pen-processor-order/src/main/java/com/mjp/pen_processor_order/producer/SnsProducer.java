package com.mjp.pen_processor_order.producer;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

import java.util.concurrent.CompletableFuture;

@Component
public class SnsProducer {

    private final SnsAsyncClient snsAsyncClient;


    public SnsProducer(SnsAsyncClient snsAsyncClient) {
        this.snsAsyncClient = snsAsyncClient;
    }


    public void publishToTopic(String topicArn, String message) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(message)
                .build();

        CompletableFuture<Void> future = snsAsyncClient.publish(request)
                .thenAccept(response -> System.out.println("Message sent! ID: " + response.messageId()))
                .exceptionally(ex -> {
                    System.err.println("Failed to send message: " + ex.getMessage());
                    return null;
                });

        // Apenas para demonstração; não bloqueie em produção
        future.join();
    }
}
