package com.mjp.factory_ball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball.entities.FactoryBall;
import com.mjp.factory_ball.repository.FactoryBallRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryBallService {

    @Value("${production.delay-millis}")
    private Integer millisDelay;

    private final FactoryBallRepository repository;
    private final ObjectMapper objectMapper;

    public FactoryBallService(FactoryBallRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public void createObject(String message) {
        List<FactoryBall> factoryBalls = extractMessageData(message);
        System.out.println("Construindo objeto ball do pedido: " + factoryBalls.getFirst().getOrderNumber());
        repository.saveAll(factoryBalls);
    }

    private List<FactoryBall> extractMessageData(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryBall> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());

                JsonNode node = penNode.get("ball");

                if (node != null) {
                    try {
                        FactoryBall object = objectMapper.treeToValue(node, FactoryBall.class);
                        object.setOrderNumber(orderNumber);
                        list.add(object);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                //Atraso de 10seg para simular numero alto de produção para obrigar a subir mais maquinas
                try {
                    Thread.sleep(quantity * millisDelay);
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
