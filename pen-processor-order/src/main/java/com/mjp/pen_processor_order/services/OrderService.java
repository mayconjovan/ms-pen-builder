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
import com.mjp.pen_processor_order.types.PaymentType;
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
                            throw new RuntimeException(e); //tratar exception
                        }
                    }).toList();

            processOrder.setPens(pens);

            var process = repository.save(processOrder);

            return OrderProcessDTO.fromEntity(process, orderRequest.pensDetails());

        } catch (Exception e) {
            e.printStackTrace(); // Precisa ser customizada
        }
        return null;
    }



    public void startProductionProcess() throws JsonProcessingException {
        List<OrderProcess> paidOrders = repository.findAllOrderProcessByPaymentStatusType(PaymentStatusType.WAITING_PAYMENT);

        paidOrders.forEach(System.out::println);

        //return notifyProduction(paidOrders);

    }

//    private void notifyProduction(List<OrderProcess> paidOrders) throws JsonProcessingException {
//        paidOrders.stream().map(process -> {
//            List<PensDetails> penDTOs = process.getPens().stream()
//                    .map(pen -> new PensDetails(pen.getId(), pen.getPenDetails()))  // Ajuste os parâmetros de acordo com a estrutura do PenDTO
//                    .collect(Collectors.toList());
//
//
//
//                }
//
//        );
//
//        OrderProcessDTO orderProcessDTO = new OrderProcessDTO(processSaved.getOrderNumber(),
//                process.getOrderCreatedAt(), process.getTotalValue(),
//                process.getQuantity(), penDTO);
//
//        String jsonMessage = objectMapper.writeValueAsString(orderProcessDTO);
//
//        snsProducer.publishMessage(jsonMessage);
        //return orderProcessDTO;
//    }


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
