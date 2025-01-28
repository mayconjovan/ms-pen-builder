package com.mjp.factory_external_tube.consumer;

import com.mjp.factory_external_tube.service.FactoryExternalTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryExternalTubeService service;

    public SqsConsumer(FactoryExternalTubeService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }


}
