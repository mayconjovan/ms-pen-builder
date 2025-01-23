package com.mjp.factory_ball.consumer;

import com.mjp.factory_ball.records.FactoryBallDetails;
import com.mjp.factory_ball.service.FactoryBallService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallService service;

    public SqsConsumer(FactoryBallService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(FactoryBallDetails FactoryBallDetails) {
        service.createFactoryBall(FactoryBallDetails);
    }

}
