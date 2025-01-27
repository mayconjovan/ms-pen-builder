package com.mjp.factory_ball.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ball.entities.FactoryBall;
import com.mjp.factory_ball.service.FactoryBallService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SqsConsumer {

    private final FactoryBallService service;
    private final ObjectMapper objectMapper;

    public SqsConsumer(FactoryBallService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(String message) {
        service.createFactoryBall(processMessage(message));
    }

    private List<FactoryBall> processMessage(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");

            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumber = innerJsonNode.get("orderNumber");
            Integer orderNumberInt = Integer.parseInt(orderNumber.asText());

            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryBall> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                JsonNode ballNode = penNode.get("ball");

                if(ballNode != null){
                    try{
                        FactoryBall factoryBall = objectMapper.treeToValue(ballNode, FactoryBall.class);
                        factoryBall.setOrderNumber(orderNumberInt);
                        list.add(factoryBall);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
                //Atraso de 10seg para simular numero alto de produção para obrigar a subir mais maquinas
                try {
                    Thread.sleep(10000);
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
