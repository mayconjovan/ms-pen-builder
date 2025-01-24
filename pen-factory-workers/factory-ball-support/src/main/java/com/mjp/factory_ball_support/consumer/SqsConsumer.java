package com.mjp.factory_ball_support.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball_support.records.FactoryBallSupportDetails;
import com.mjp.factory_ball_support.service.FactoryBallSupportService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallSupportService service;

    public SqsConsumer(FactoryBallSupportService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(String message) {
        service.createFactoryBallSupport(processMessage(message));
    }

    private FactoryBallSupportDetails processMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");

            String innerMessage = messageNode.asText();

            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode ballNode = innerJsonNode.get("ballSupport");

            return objectMapper.treeToValue(ballNode, FactoryBallSupportDetails.class);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
