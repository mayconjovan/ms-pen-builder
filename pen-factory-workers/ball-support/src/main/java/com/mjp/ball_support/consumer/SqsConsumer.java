package com.mjp.ball_support.consumer;

import com.mjp.ball_support.records.BallSupportDetails;
import com.mjp.ball_support.service.BallSupportService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final BallSupportService service;

    public SqsConsumer(BallSupportService service) {
        this.service = service;
    }

    @SqsListener("factory-ball-support")
    public void listen(BallSupportDetails ballSupportDetails) {
        service.createBallSupport(ballSupportDetails);
    }

}
