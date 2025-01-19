package com.mjp.ball_support_coupler.consumer;

import com.mjp.ball_support_coupler.records.BallSupportCouplerDetails;
import com.mjp.ball_support_coupler.service.BallSupportCouplerService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final BallSupportCouplerService service;

    public SqsConsumer(BallSupportCouplerService service) {
        this.service = service;
    }

    @SqsListener("factory-ball-support-coupler")
    public void listen(BallSupportCouplerDetails ballSupportDetails) {
        service.createBallSupportCoupler(ballSupportDetails);
    }

}
