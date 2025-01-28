package com.mjp.factory_internal_tube.consumer;

import com.mjp.factory_internal_tube.service.FactoryInternalTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryInternalTubeService service;

    public SqsConsumer(FactoryInternalTubeService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }

}
