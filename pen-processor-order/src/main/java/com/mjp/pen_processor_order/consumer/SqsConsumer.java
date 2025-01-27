package com.mjp.pen_processor_order.consumer;

import com.mjp.pen_processor_order.services.OrderService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final OrderService service;

    public SqsConsumer(OrderService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.listener}")
    public void listen(String message) {
        service.startProductionProcess();
    }

}
