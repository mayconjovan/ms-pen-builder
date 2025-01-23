package com.mjp.factory_ball_support_coupler_ink_tube.consumer;

import com.mjp.factory_ball_support_coupler_ink_tube.records.FactoryBallSupportCouplerInkTubeDetails;
import com.mjp.factory_ball_support_coupler_ink_tube.service.FactoryBallSupportCouplerInkTubeService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallSupportCouplerInkTubeService service;

    public SqsConsumer(FactoryBallSupportCouplerInkTubeService service) {
        this.service = service;
    }

    @SqsListener("factory-ball-support-coupler-ink-tube")
    public void listen(FactoryBallSupportCouplerInkTubeDetails details) {
        service.createBallSupportCoupler(details);
    }

}
