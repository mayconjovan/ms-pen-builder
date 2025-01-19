package com.mjp.tip_cap.consumer;

import com.mjp.tip_cap.records.TipCapDetails;
import com.mjp.tip_cap.service.TipCapService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final TipCapService service;

    public SqsConsumer(TipCapService service) {
        this.service = service;
    }

    @SqsListener("factory-tip-cap")
    public void listen(TipCapDetails details) {
        service.createBallSupportCoupler(details);
    }

}
