package com.mjp.factory_ball_support_coupler.consumer;

import com.mjp.factory_ball_support_coupler.records.FactoryBallSupportCouplerDetails;
import com.mjp.factory_ball_support_coupler.service.FactoryBallSupportCouplerService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final FactoryBallSupportCouplerService service;

    public SqsConsumer(FactoryBallSupportCouplerService service) {
        this.service = service;
    }

    @SqsListener("factory-ball-support-coupler")
    public void listen(FactoryBallSupportCouplerDetails ballSupportDetails) {
        service.createBallSupportCoupler(ballSupportDetails);
    }

}
