package com.mjp.external_tube.service;

import com.mjp.external_tube.entities.ExternalTube;
import com.mjp.external_tube.records.ExternalTubeDetails;
import com.mjp.external_tube.repository.ExternalTubeRepository;
import org.springframework.stereotype.Service;

@Service
public class ExternalTubeService {

    private final ExternalTubeRepository repository;

    public ExternalTubeService(ExternalTubeRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(ExternalTubeDetails details){
        var entity = new ExternalTube(null, "suporte da esfera",
                details.size(), details.materialType());

        repository.save(entity);
    }
}
