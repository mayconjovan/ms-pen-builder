package com.mjp.outer_tube_cover.consumer;

import com.mjp.outer_tube_cover.records.OuterTubeCoverDetails;
import com.mjp.outer_tube_cover.service.OuterTubeCoverService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final OuterTubeCoverService service;

    public SqsConsumer(OuterTubeCoverService service) {
        this.service = service;
    }

    @SqsListener("factory-outer-tube-cover")
    public void listen(OuterTubeCoverDetails details) {
        service.createBallSupportCoupler(details);
    }

}
