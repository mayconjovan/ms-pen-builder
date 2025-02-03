package com.mjp.factory_ball.consumer;

import com.mjp.factory_ball.service.FactoryPenService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryPenService service;

    public SqsConsumer(FactoryPenService service) {
        this.service = service;
    }

    @SqsListener("${queue-listener}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }


}
