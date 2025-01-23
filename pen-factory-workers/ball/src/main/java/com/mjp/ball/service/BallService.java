package com.mjp.ball.service;

import com.mjp.ball.entities.Ball;
import com.mjp.ball.records.BallDetails;
import com.mjp.ball.repository.BallRepository;
import org.springframework.stereotype.Service;

@Service
public class BallService {

    private final BallRepository repository;

    public BallService(BallRepository repository) {
        this.repository = repository;
    }


    public void createBall(BallDetails ball){
        var entity = new Ball(null, "esfera", ball.size(), ball.materialType());

        repository.save(entity);
    }
}
