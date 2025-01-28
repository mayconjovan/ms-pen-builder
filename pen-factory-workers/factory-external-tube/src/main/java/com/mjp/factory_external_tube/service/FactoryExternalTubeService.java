package com.mjp.factory_external_tube.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_external_tube.entities.FactoryExternalTube;
import com.mjp.factory_external_tube.repository.FactoryExternalTubeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryExternalTubeService {

    @Value("${production.delay-millis}")
    private Integer millisDelay;

    private final ObjectMapper objectMapper;
    private final FactoryExternalTubeRepository repository;

    public FactoryExternalTubeService(ObjectMapper objectMapper, FactoryExternalTubeRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }


    public void createObject(String message) {
        List<FactoryExternalTube> list = extractMessageData(message);
        System.out.println("Construindo objeto ball support do pedido: " + list.getFirst().getOrderNumber());
        repository.saveAll(list);
    }

    private List<FactoryExternalTube> extractMessageData(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryExternalTube> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());
                JsonNode node = penNode.get("externalTube");

                if (node != null) {
                    try {
                        FactoryExternalTube object = objectMapper.treeToValue(node, FactoryExternalTube.class);
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
