package com.mjp.factory_ball_support_coupler.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball_support_coupler.records.FactoryBallSupportCouplerDetails;
import com.mjp.factory_ball_support_coupler.service.FactoryBallSupportCouplerService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallSupportCouplerService service;

    public SqsConsumer(FactoryBallSupportCouplerService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(String message) {
        service.createFactoryBallSupportCoupler(processMessage(message));
    }

    private FactoryBallSupportCouplerDetails processMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");

            String innerMessage = messageNode.asText();

            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode ballNode = innerJsonNode.get("ballSupportCoupler");

            return objectMapper.treeToValue(ballNode, FactoryBallSupportCouplerDetails.class);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
