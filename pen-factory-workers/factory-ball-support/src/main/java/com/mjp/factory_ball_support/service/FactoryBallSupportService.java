package com.mjp.factory_ball_support.service;

import com.mjp.factory_ball_support.entities.FactoryBallSupport;
import com.mjp.factory_ball_support.records.FactoryBallSupportDetails;
import com.mjp.factory_ball_support.repository.FactoryBallSupportRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryBallSupportService {

    private final FactoryBallSupportRepository repository;

    public FactoryBallSupportService(FactoryBallSupportRepository repository) {
        this.repository = repository;
    }


    public void createFactoryBallSupport(FactoryBallSupportDetails factoryBallSupport){
        repository.save(new FactoryBallSupport(factoryBallSupport));
    }
}
