package com.mjp.factory_outer_tube_cover.consumer;

import com.mjp.factory_outer_tube_cover.records.FactoryOuterTubeCoverDetails;
import com.mjp.factory_outer_tube_cover.service.FactoryOuterTubeCoverService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryOuterTubeCoverService service;

    public SqsConsumer(FactoryOuterTubeCoverService service) {
        this.service = service;
    }

    @SqsListener("factory-outer-tube-cover")
    public void listen(FactoryOuterTubeCoverDetails details) {
        service.createBallSupportCoupler(details);
    }

}
