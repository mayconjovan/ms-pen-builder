package com.mjp.internal_tube.service;

import com.mjp.internal_tube.entities.InternalTube;
import com.mjp.internal_tube.records.InternalTubeDetails;
import com.mjp.internal_tube.repository.InternalTubeRepository;
import org.springframework.stereotype.Service;

@Service
public class InternalTubeService {

    private final InternalTubeRepository repository;

    public InternalTubeService(InternalTubeRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(InternalTubeDetails details){
        var entity = new InternalTube(null, "suporte da esfera",
                details.size(), details.materialType());

        repository.save(entity);
    }
}
