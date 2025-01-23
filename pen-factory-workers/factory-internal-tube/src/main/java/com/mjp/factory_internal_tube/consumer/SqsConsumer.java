package com.mjp.factory_internal_tube.consumer;

import com.mjp.factory_internal_tube.records.FactoryInternalTubeDetails;
import com.mjp.factory_internal_tube.service.FactoryInternalTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryInternalTubeService service;

    public SqsConsumer(FactoryInternalTubeService service) {
        this.service = service;
    }

    @SqsListener("factory-internal-tube")
    public void listen(FactoryInternalTubeDetails details) {
        service.createBallSupportCoupler(details);
    }

}
