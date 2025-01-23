package com.mjp.factory_external_tube.consumer;

import com.mjp.factory_external_tube.records.FactoryExternalTubeDetails;
import com.mjp.factory_external_tube.service.FactoryExternalTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryExternalTubeService service;

    public SqsConsumer(FactoryExternalTubeService service) {
        this.service = service;
    }

    @SqsListener("factory-external-tube")
    public void listen(FactoryExternalTubeDetails details) {
        service.createBallSupportCoupler(details);
    }

}
