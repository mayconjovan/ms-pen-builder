package com.mjp.pen_processor_order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.PenDTO;
import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.producer.SnsPublisher;
import com.mjp.pen_processor_order.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final SnsPublisher snsProducer;


    public OrderService(OrderRepository repository, SnsPublisher snsProducer) {
        this.repository = repository;
        this.snsProducer = snsProducer;
    }

    public OrderProcessDTO createProductionOrder(PenDTO penDTO, Integer quantity) {
        try {
            var process = new OrderProcess(null, Instant.now(), null, quantity);
            var processSaved = repository.save(process);
            return notifyProduction(penDTO, processSaved, process);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar ordem de produção");
        }
    }

    private OrderProcessDTO notifyProduction(PenDTO penDTO, OrderProcess processSaved, OrderProcess process) throws JsonProcessingException {
        OrderProcessDTO orderProcessDTO = new OrderProcessDTO(processSaved.getOrderNumber(),
                process.getOrderCreatedAt(), process.getTotalValue(),
                process.getQuantity(), penDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(orderProcessDTO);

        snsProducer.publishMessage(jsonMessage);
        return orderProcessDTO;
    }


//    public List<OrderProcessDTO> listAllOrders() {
//
//        List<OrderProcessDTO> dtos = new ArrayList<>();
//        List<OrderProcess> all = repository.findAll();
//        //Aqui deve ler no outro banco de dados dos workers
//
//        //all.forEach(OrderProcessDTO::new);
//
//        return null;
//
//    }
}
