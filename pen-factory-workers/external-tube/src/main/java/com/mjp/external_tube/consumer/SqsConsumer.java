package com.mjp.external_tube.consumer;

import com.mjp.external_tube.records.ExternalTubeDetails;
import com.mjp.external_tube.service.ExternalTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final ExternalTubeService service;

    public SqsConsumer(ExternalTubeService service) {
        this.service = service;
    }

    @SqsListener("factory-external-tube")
    public void listen(ExternalTubeDetails details) {
        service.createBallSupportCoupler(details);
    }

}
