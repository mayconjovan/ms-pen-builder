package com.mjp.coupler_support_ball_ink_tube.consumer;

import com.mjp.coupler_support_ball_ink_tube.records.BallSupportCouplerInkTubeDetails;
import com.mjp.coupler_support_ball_ink_tube.service.BallSupportCouplerInkTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final BallSupportCouplerInkTubeService service;

    public SqsConsumer(BallSupportCouplerInkTubeService service) {
        this.service = service;
    }

    @SqsListener("factory-ball-support-coupler-ink-tube")
    public void listen(BallSupportCouplerInkTubeDetails details) {
        service.createBallSupportCoupler(details);
    }

}
