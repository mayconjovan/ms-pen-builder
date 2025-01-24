package com.mjp.factory_ball.service;

import com.mjp.factory_ball.entities.FactoryBall;
import com.mjp.factory_ball.records.FactoryBallDetails;
import com.mjp.factory_ball.repository.FactoryBallRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryBallService {

    private final FactoryBallRepository repository;

    public FactoryBallService(FactoryBallRepository repository) {
        this.repository = repository;
    }


    public void createFactoryBall(FactoryBallDetails factoryBallDetails){
        repository.save(new FactoryBall(factoryBallDetails));
    }
}
