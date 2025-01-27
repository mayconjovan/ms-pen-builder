package com.mjp.pen_payment_validator.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.pen_payment_validator.service.PaymentValidatorService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final PaymentValidatorService service;
    private final ObjectMapper objectMapper;

    public SqsConsumer(PaymentValidatorService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(String message) {
        service.validatePayment(processMessage(message));
    }

    private Long processMessage(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");

            String innerMessage = messageNode.asText();

            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode payment = innerJsonNode.get("orderNumber");

            return objectMapper.treeToValue(payment, Long.class);


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
