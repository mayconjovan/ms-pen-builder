package com.mjp.factory_ball_support_coupler.service;

import com.mjp.factory_ball_support_coupler.entities.FactoryBallSupportCoupler;
import com.mjp.factory_ball_support_coupler.records.FactoryBallSupportCouplerDetails;
import com.mjp.factory_ball_support_coupler.repository.FactoryBallSupportCouplerRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryBallSupportCouplerService {

    private final FactoryBallSupportCouplerRepository repository;

    public FactoryBallSupportCouplerService(FactoryBallSupportCouplerRepository repository) {
        this.repository = repository;
    }


    public void createFactoryBallSupportCoupler(FactoryBallSupportCouplerDetails ballSupportCoupler){
        repository.save(new FactoryBallSupportCoupler(ballSupportCoupler));
    }
}
