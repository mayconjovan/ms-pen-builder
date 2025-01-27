package com.mjp.factory_ball.service;

import com.mjp.factory_ball.entities.FactoryBall;
import com.mjp.factory_ball.repository.FactoryBallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryBallService {

    private final FactoryBallRepository repository;

    public FactoryBallService(FactoryBallRepository repository) {
        this.repository = repository;
    }


    public void createFactoryBall(List<FactoryBall> factoryBall) {
        System.out.println("Construindo objeto ball do pedido: " + factoryBall.getFirst().getOrderNumber());
        repository.saveAll(factoryBall);
    }
}
