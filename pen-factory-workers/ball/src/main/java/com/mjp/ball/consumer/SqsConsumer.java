package com.mjp.ball.consumer;

import com.mjp.ball.records.BallDetails;
import com.mjp.ball.service.BallService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private BallService service;

    public SqsConsumer(BallService service) {
        this.service = service;
    }

    @SqsListener("factory-ball")
    public void listen(BallDetails ballDetails) {
        service.createBall(ballDetails);
    }

}
