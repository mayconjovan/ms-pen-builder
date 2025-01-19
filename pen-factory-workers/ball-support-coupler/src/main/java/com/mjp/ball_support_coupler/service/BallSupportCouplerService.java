package com.mjp.ball_support_coupler.service;

import com.mjp.ball_support_coupler.entities.BallSupportCoupler;
import com.mjp.ball_support_coupler.records.BallSupportCouplerDetails;
import com.mjp.ball_support_coupler.repository.BallSupportCouplerRepository;
import org.springframework.stereotype.Service;

@Service
public class BallSupportCouplerService {

    private final BallSupportCouplerRepository repository;

    public BallSupportCouplerService(BallSupportCouplerRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(BallSupportCouplerDetails ballSupportCoupler){
        var entity = new BallSupportCoupler(null, "suporte da esfera", ballSupportCoupler.size(), ballSupportCoupler.materialType());

        repository.save(entity);
    }
}
