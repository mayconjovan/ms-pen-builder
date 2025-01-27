package com.mjp.pen_processor_order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjp.pen_processor_order.dto.OrderProcessDTO;
import com.mjp.pen_processor_order.dto.PensDetailsDTO;
import com.mjp.pen_processor_order.dto.ProductionOrderRequest;
import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.entities.PaymentDetails;
import com.mjp.pen_processor_order.entities.Pen;
import com.mjp.pen_processor_order.producer.SnsPublisher;
import com.mjp.pen_processor_order.producer.SqsPublisher;
import com.mjp.pen_processor_order.repositories.OrderRepository;
import com.mjp.pen_processor_order.types.PaymentStatusType;
import com.mjp.pen_processor_order.util.EntityManagerHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Service
public class OrderService {

    public static final String ORDER_NUMBER_SEQ = "order_number_seq";
    private final OrderRepository repository;
    private final SnsPublisher snsProducer;
    private final EntityManagerHelper entityManger;
    private final ObjectMapper objectMapper;
    private final SqsPublisher sqsPublisher;


    public OrderService(OrderRepository repository, SnsPublisher snsProducer, EntityManagerHelper entityManger, ObjectMapper objectMapper, SqsPublisher sqsPublisher) {
        this.repository = repository;
        this.snsProducer = snsProducer;
        this.entityManger = entityManger;
        this.objectMapper = objectMapper;
        this.sqsPublisher = sqsPublisher;
    }

    public OrderProcessDTO createOrder(ProductionOrderRequest orderRequest) {
        try {

            var processOrder = OrderProcess.builder()
                    .orderNumber(entityManger.getNextSequenceValue(ORDER_NUMBER_SEQ))
                    .build();
            processOrder.setPaymentDetails(new PaymentDetails(orderRequest.paymentDetails(), processOrder));

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

            processPayment(processOrder.getOrderNumber());

            return OrderProcessDTO.fromEntity(process, orderRequest.pensDetails());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar o JSON da caneta", e); // Precisa ser customizada
        }
    }

    private void processPayment(Integer orderNumber) {
            sqsPublisher.sendMessage(String.valueOf(orderNumber));
    }


    public List<Integer> startProductionProcess() {
        System.out.println("Start production process");
        List<OrderProcess> paidOrders = repository.findAllByPaymentDetails_PaymentStatusType(PaymentStatusType.APPROVED_PAYMENT);
        List<OrderProcessDTO> orderProcessDTOs = convertEntityToDto(paidOrders, Function.identity());
        notifyProduction(orderProcessDTOs);
        return orderProcessDTOs.stream().map(OrderProcessDTO::orderNumber).toList();
    }

    public Page<OrderProcessDTO> findAllOrdersPaged(Pageable pageable, String paymentStatus) {
        Page<OrderProcess> orders;

        if (paymentStatus != null) {
            orders = repository.findAllByPaymentDetails_PaymentStatusType(PaymentStatusType.valueOf(paymentStatus), pageable);
        } else {
            orders = repository.findAll(pageable);
        }

        return convertEntityToDto(orders.getContent(),
                dtoList -> new PageImpl<>(dtoList, pageable, orders.getTotalElements()));
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

    private <T> T convertEntityToDto(Collection<OrderProcess> paidOrders, Function<List<OrderProcessDTO>, T> converter) {
        List<OrderProcessDTO> dtoList = paidOrders.stream()
                .map(orderProcess -> {
                    List<PensDetailsDTO> penDetailsList = orderProcess.getPens().stream()
                            .map(pen -> {
                                try {
                                    return objectMapper.readValue(pen.getPenDetails(), PensDetailsDTO.class);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException("Erro ao processar o JSON da caneta", e);
                                }
                            })
                            .toList();
                    return OrderProcessDTO.fromEntity(orderProcess, penDetailsList);
                })
                .toList();

        return converter.apply(dtoList);
    }

}
