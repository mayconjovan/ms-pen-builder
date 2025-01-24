package com.mjp.factory_ball.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball.records.FactoryBallDetails;
import com.mjp.factory_ball.service.FactoryBallService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallService service;

    public SqsConsumer(FactoryBallService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(String message) {
        service.createFactoryBall(processMessage(message));
    }

    private FactoryBallDetails processMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");

            String innerMessage = messageNode.asText();

            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode ballNode = innerJsonNode.get("ball");

            return objectMapper.treeToValue(ballNode, FactoryBallDetails.class);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
