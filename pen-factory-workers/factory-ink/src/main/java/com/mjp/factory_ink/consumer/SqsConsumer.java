package com.mjp.factory_ink.consumer;

import com.mjp.factory_ink.service.FactoryInkService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryInkService service;

    public SqsConsumer(FactoryInkService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }

}
