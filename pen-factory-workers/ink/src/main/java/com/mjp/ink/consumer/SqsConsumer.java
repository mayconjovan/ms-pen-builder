package com.mjp.ink.consumer;

import com.mjp.ink.records.InkDetails;
import com.mjp.ink.service.InkService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final InkService service;

    public SqsConsumer(InkService service) {
        this.service = service;
    }

    @SqsListener("factory-ink")
    public void listen(InkDetails details) {
        service.createBallSupportCoupler(details);
    }

}
