package com.mjp.factory_tip_cap.consumer;

import com.mjp.factory_tip_cap.records.FactoryTipCapDetails;
import com.mjp.factory_tip_cap.service.FactoryTipCapService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryTipCapService service;

    public SqsConsumer(FactoryTipCapService service) {
        this.service = service;
    }

    @SqsListener("factory-tip-cap")
    public void listen(FactoryTipCapDetails details) {
        service.createBallSupportCoupler(details);
    }

}
