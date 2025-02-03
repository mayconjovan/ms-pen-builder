package com.mjp.factory_ball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball.entities.Ball;
import com.mjp.factory_ball.repository.FactoryPenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryPenService {

    @Value("${delay-millis}")
    private Integer delayProduction;

    private final FactoryPenRepository repository;
    private final ObjectMapper objectMapper;

    public FactoryPenService(FactoryPenRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public void createObject(String message) {
        List<Ball> balls = extractMessageData(message);
        System.out.println("Construindo objeto ball do pedido: " + balls.getFirst().getOrderNumber());
        repository.saveAll(balls);
    }

    private List<Ball> extractMessageData(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<Ball> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());

                JsonNode node = penNode.get("ball");

                if (node != null) {
                    try {
                        Ball object = objectMapper.treeToValue(node, Ball.class);
                        object.setOrderNumber(orderNumber);
                        list.add(object);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    Thread.sleep(quantity * delayProduction);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            return list;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
