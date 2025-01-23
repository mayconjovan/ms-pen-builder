package com.mjp.factory_ball_support_coupler_ink_tube.service;

import com.mjp.factory_ball_support_coupler_ink_tube.entities.FactoryBallSupportCouplerInkTube;
import com.mjp.factory_ball_support_coupler_ink_tube.records.FactoryBallSupportCouplerInkTubeDetails;
import com.mjp.factory_ball_support_coupler_ink_tube.repository.FactoryBallSupportCouplerInkTubeRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryBallSupportCouplerInkTubeService {

    private final FactoryBallSupportCouplerInkTubeRepository repository;

    public FactoryBallSupportCouplerInkTubeService(FactoryBallSupportCouplerInkTubeRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(FactoryBallSupportCouplerInkTubeDetails ballSupportCoupler){
        var entity = new FactoryBallSupportCouplerInkTube(null, "suporte da esfera",
                ballSupportCoupler.materialType());

        repository.save(entity);
    }
}
