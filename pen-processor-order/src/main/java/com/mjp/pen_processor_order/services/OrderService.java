package com.mjp.pen_processor_order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.PenDTO;
import com.mjp.pen_processor_order.dto.ProductionOrderRequest;
import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.producer.SnsPublisher;
import com.mjp.pen_processor_order.repositories.OrderRepository;
import com.mjp.pen_processor_order.util.EntityManagerHelper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OrderService {

    public static final String ORDER_NUMBER_SEQ = "order_number_seq";
    private final OrderRepository repository;
    private final SnsPublisher snsProducer;
    private final EntityManagerHelper entityManger;
    private final ObjectMapper objectMapper;


    public OrderService(OrderRepository repository, SnsPublisher snsProducer, EntityManagerHelper entityManger, ObjectMapper objectMapper) {
        this.repository = repository;
        this.snsProducer = snsProducer;
        this.entityManger = entityManger;
        this.objectMapper = objectMapper;
    }

    public OrderProcessDTO createProductionOrder(ProductionOrderRequest orderRequest) {
        try {
            var process = new OrderProcess(null, Instant.now(), entityManger.getNextSequenceValue(ORDER_NUMBER_SEQ),
                    orderRequest.quantity());

            var processSaved = repository.save(process);
            return notifyProduction(orderRequest.penDetails(), processSaved, process);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private OrderProcessDTO notifyProduction(PenDTO penDTO, OrderProcess processSaved, OrderProcess process) throws JsonProcessingException {
        OrderProcessDTO orderProcessDTO = new OrderProcessDTO(processSaved.getOrderNumber(),
                process.getOrderCreatedAt(), process.getTotalValue(),
                process.getQuantity(), penDTO);

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
