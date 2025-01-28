package com.mjp.factory_outer_tube_cover.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory_outer_tube_cover.entities.FactoryOuterTubeCover;
import com.mjp.factory_outer_tube_cover.repository.FactoryOuterTubeCoverRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FactoryOuterTubeCoverService {

    @Value("${production.delay-millis}")
    private Integer millisDelay;

    private final ObjectMapper objectMapper;

    private final FactoryOuterTubeCoverRepository repository;

    public FactoryOuterTubeCoverService(ObjectMapper objectMapper, FactoryOuterTubeCoverRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }


    public void createObject(String message){
        List<FactoryOuterTubeCover> list = extractMessageData(message);
        System.out.println("Construindo objeto outer tube cover do pedido: " + list.getFirst().getOrderNumber());
        repository.saveAll(list);
    }

    private List<FactoryOuterTubeCover> extractMessageData(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<FactoryOuterTubeCover> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());
                JsonNode node = penNode.get("outerTubeCover");

                if(node != null){
                    try{
                        FactoryOuterTubeCover object = objectMapper.treeToValue(node, FactoryOuterTubeCover.class);
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
