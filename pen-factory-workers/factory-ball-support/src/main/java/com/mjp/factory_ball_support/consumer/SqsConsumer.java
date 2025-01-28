package com.mjp.factory_ball_support.consumer;

import com.mjp.factory_ball_support.service.FactoryBallSupportService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallSupportService service;

    public SqsConsumer(FactoryBallSupportService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }

}
