package com.mjp.coupler_support_ball_ink_tube.service;

import com.mjp.coupler_support_ball_ink_tube.entities.BallSupportCouplerInkTube;
import com.mjp.coupler_support_ball_ink_tube.records.BallSupportCouplerInkTubeDetails;
import com.mjp.coupler_support_ball_ink_tube.repository.BallSupportCouplerInkTubeRepository;
import org.springframework.stereotype.Service;

@Service
public class BallSupportCouplerInkTubeService {

    private final BallSupportCouplerInkTubeRepository repository;

    public BallSupportCouplerInkTubeService(BallSupportCouplerInkTubeRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(BallSupportCouplerInkTubeDetails ballSupportCoupler){
        var entity = new BallSupportCouplerInkTube(null, "suporte da esfera",
                ballSupportCoupler.size(), ballSupportCoupler.materialType());

        repository.save(entity);
    }
}
