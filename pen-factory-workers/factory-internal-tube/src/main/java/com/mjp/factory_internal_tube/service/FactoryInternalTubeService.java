package com.mjp.factory_internal_tube.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_internal_tube.entities.FactoryInternalTube;
import com.mjp.factory_internal_tube.repository.FactoryInternalTubeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryInternalTubeService {

    @Value("${production.delay-millis}")
    private Integer millisDelay;

    private final ObjectMapper objectMapper;

    private final FactoryInternalTubeRepository repository;

    public FactoryInternalTubeService(ObjectMapper objectMapper, FactoryInternalTubeRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }


    public void createObject(String message){
        List<FactoryInternalTube> list = extractMessageData(message);
        System.out.println("Construindo objeto internal tube do pedido: " + list.getFirst().getOrderNumber());
        repository.saveAll(list);
    }

    private List<FactoryInternalTube> extractMessageData(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");

            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());

            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryInternalTube> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());
                JsonNode node = penNode.get("internalTube");

                if(node != null){
                    try{
                        FactoryInternalTube object = objectMapper.treeToValue(node, FactoryInternalTube.class);
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
