package com.mjp.pen_processor_order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.PensDetails;
import com.mjp.pen_processor_order.dto.ProductionOrderRequest;
import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.entities.Pen;
import com.mjp.pen_processor_order.producer.SnsPublisher;
import com.mjp.pen_processor_order.repositories.OrderRepository;
import com.mjp.pen_processor_order.types.PaymentStatusType;
import com.mjp.pen_processor_order.util.EntityManagerHelper;
import org.springframework.stereotype.Service;

import java.util.List;

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

            var processOrder = OrderProcess.builder()
                    .orderNumber(entityManger.getNextSequenceValue(ORDER_NUMBER_SEQ))
                    .paymentType(orderRequest.paymentType())
                    .build();

            var pens = orderRequest.pensDetails().stream()
                    .map(penDetails -> {
                        try {
                            return Pen.builder().penDetails(objectMapper.writeValueAsString(penDetails))
                                    .orderProcess(processOrder)
                                    .build();
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException("Erro: JSON precisa ser revisado", e); //tratar exception
                        }
                    }).toList();

            processOrder.setPens(pens);

            var process = repository.save(processOrder);

            return OrderProcessDTO.fromEntity(process, orderRequest.pensDetails());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o JSON da caneta", e); // Precisa ser customizada
        }
    }


    public List<Integer> startProductionProcess() {
        List<OrderProcess> paidOrders = repository.findAllOrderProcessByPaymentStatusType(PaymentStatusType.WAITING_PAYMENT);
        List<OrderProcessDTO> orderProcessDTOs = convertEntityToDto(paidOrders);
        notifyProduction(orderProcessDTOs);
        return orderProcessDTOs.stream().map(OrderProcessDTO::orderNumber).toList();
    }

    private List<OrderProcessDTO> convertEntityToDto(List<OrderProcess> paidOrders) {
        return paidOrders.stream()
                .map(orderProcess -> {
                    List<PensDetails> penDetailsList = orderProcess.getPens().stream()
                            .map(pen -> {
                                try {
                                    return objectMapper.readValue(pen.getPenDetails(), PensDetails.class);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException("Erro ao processar o JSON da caneta", e);
                                }
                            })
                            .toList();
                    return new OrderProcessDTO(
                            orderProcess.getOrderNumber(),
                            orderProcess.getOrderCreatedAt(),
                            orderProcess.getTotalValue(),
                            orderProcess.getPaymentType(),
                            penDetailsList
                    );
                })
                .toList();
    }

    private void notifyProduction(List<OrderProcessDTO> paidOrders) {
        paidOrders.forEach(x -> {
            String jsonMessage;
            try {
                jsonMessage = objectMapper.writeValueAsString(x);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            snsProducer.publishMessage(jsonMessage);
        });
    }

    public List<OrderProcessDTO> listAllOrdersPaged() {
       return convertEntityToDto(repository.findAll());
    }
}
