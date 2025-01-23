package com.mjp.internal_tube.consumer;

import com.mjp.internal_tube.records.InternalTubeDetails;
import com.mjp.internal_tube.service.InternalTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final InternalTubeService service;

    public SqsConsumer(InternalTubeService service) {
        this.service = service;
    }

    @SqsListener("factory-internal-tube")
    public void listen(InternalTubeDetails details) {
        service.createBallSupportCoupler(details);
    }

}
