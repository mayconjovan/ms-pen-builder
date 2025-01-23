package com.mjp.factory_ink.consumer;

import com.mjp.factory_ink.records.FactoryInkDetails;
import com.mjp.factory_ink.service.FactoryInkService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryInkService service;

    public SqsConsumer(FactoryInkService service) {
        this.service = service;
    }

    @SqsListener("factory-ink")
    public void listen(FactoryInkDetails details) {
        service.createBallSupportCoupler(details);
    }

}
