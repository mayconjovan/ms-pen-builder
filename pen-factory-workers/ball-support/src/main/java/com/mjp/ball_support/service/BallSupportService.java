package com.mjp.ball_support.service;

import com.mjp.ball_support.entities.BallSupport;
import com.mjp.ball_support.records.BallSupportDetails;
import com.mjp.ball_support.repository.BallSupportRepository;
import org.springframework.stereotype.Service;

@Service
public class BallSupportService {

    private final BallSupportRepository repository;

    public BallSupportService(BallSupportRepository repository) {
        this.repository = repository;
    }


    public void createBallSupport(BallSupportDetails ballSupport){
        var entity = new BallSupport(null, "suporte da esfera", ballSupport.size(), ballSupport.materialType());

        repository.save(entity);
    }
}
