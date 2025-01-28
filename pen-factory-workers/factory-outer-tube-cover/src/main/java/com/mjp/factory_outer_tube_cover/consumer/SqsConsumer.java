package com.mjp.factory_outer_tube_cover.consumer;

import com.mjp.factory_outer_tube_cover.service.FactoryOuterTubeCoverService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryOuterTubeCoverService service;

    public SqsConsumer(FactoryOuterTubeCoverService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }

}
