package com.mjp.factory_ball_support_coupler_ink_tube.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball_support_coupler_ink_tube.entities.FactoryBallSupportCouplerInkTube;
import com.mjp.factory_ball_support_coupler_ink_tube.repository.FactoryBallSupportCouplerInkTubeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryBallSupportCouplerInkTubeService {

    @Value("${production.delay-millis}")
    private Integer millisDelay;

    private final ObjectMapper objectMapper;

    private final FactoryBallSupportCouplerInkTubeRepository repository;

    public FactoryBallSupportCouplerInkTubeService(ObjectMapper objectMapper, FactoryBallSupportCouplerInkTubeRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }


    public void createObject(String message){
        List<FactoryBallSupportCouplerInkTube> factoryBalls = extractMessageData(message);
        System.out.println("Construindo objeto ball support coupler do pedido: " +
                factoryBalls.getFirst().getOrderNumber());
        repository.saveAll(factoryBalls);
    }

    private List<FactoryBallSupportCouplerInkTube> extractMessageData(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryBallSupportCouplerInkTube> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());

                JsonNode node = penNode.get("ballSupportCouplerInkTube");

                if(node != null){
                    try{
                        FactoryBallSupportCouplerInkTube object = objectMapper.treeToValue(node, FactoryBallSupportCouplerInkTube.class);
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
