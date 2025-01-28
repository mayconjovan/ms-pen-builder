package com.mjp.factory_ink.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_ink.entities.FactoryInk;
import com.mjp.factory_ink.repository.FactoryInkRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryInkService {

    @Value("${production.delay-millis}")
    private Integer millisDelay;

    private final ObjectMapper objectMapper;
    private final FactoryInkRepository repository;


    public FactoryInkService(ObjectMapper objectMapper, FactoryInkRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }


    public void createObject(String message){
        List<FactoryInk> factoryInk = extractMessageData(message);
        System.out.println("Construindo objeto ink: " + factoryInk.getFirst().getOrderNumber());
        repository.saveAll(factoryInk);
    }

    private List<FactoryInk> extractMessageData(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryInk> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());
                JsonNode node = penNode.get("ink");

                if(node != null){
                    try{
                        FactoryInk object = objectMapper.treeToValue(node, FactoryInk.class);
                        object.setOrderNumber(orderNumber);
                        list.add(object);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }

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
