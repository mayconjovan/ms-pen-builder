package com.mjp.factory_tip_cap.consumer;

import com.mjp.factory_tip_cap.service.FactoryTipCapService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryTipCapService service;

    public SqsConsumer(FactoryTipCapService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void messageReceiver(String message) {
        service.createObject(message);
    }

}
