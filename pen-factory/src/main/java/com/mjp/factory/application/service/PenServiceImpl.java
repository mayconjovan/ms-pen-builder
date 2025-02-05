package com.mjp.factory.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.factory.application.usecases.PenUseCase;
import com.mjp.factory.domain.model.Pen;
import com.mjp.factory.domain.repositories.PenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@DependsOn("ssmConfig")
public class PenServiceImpl implements PenUseCase {

    @Value("${delay-millis}")
    private Integer delayProduction;

    private final PenRepository penRepository;
    private final ObjectMapper objectMapper;

    public PenServiceImpl(PenRepository penRepository, ObjectMapper objectMapper) {
        this.penRepository = penRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createObject(String message) {
        List<Pen> pens = extractMessageData(message);
        System.out.println("Building pens from order: " + pens.getFirst().getUuid());
        //penRepository.getPenByUUID(UUID.randomUUID());
    }

    private List<Pen> extractMessageData(String message) {
        try {

            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode messageNode = jsonNode.get("Message");
            String innerMessage = messageNode.asText();
            JsonNode innerJsonNode = objectMapper.readTree(innerMessage);
            JsonNode orderNumberNode = innerJsonNode.get("orderNumber");
            Integer orderNumber = Integer.parseInt(orderNumberNode.asText());
            JsonNode pensNode = innerJsonNode.get("pen");

            List<Pen> list = new ArrayList<>();

            pensNode.forEach(penNode -> {
                //quantity usado para calcular o atraso na produção
                JsonNode quantityNode = penNode.get("quantity");
                Long quantity = Long.parseLong(quantityNode.asText());

                JsonNode node = penNode.get("pen");

                if (node != null) {
                    try {
                        Pen object = objectMapper.treeToValue(node, Pen.class);
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
