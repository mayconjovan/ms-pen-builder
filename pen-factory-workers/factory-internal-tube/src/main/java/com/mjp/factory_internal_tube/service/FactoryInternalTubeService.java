package com.mjp.factory_internal_tube.service;

import com.mjp.factory_internal_tube.entities.FactoryInternalTube;
import com.mjp.factory_internal_tube.records.FactoryInternalTubeDetails;
import com.mjp.factory_internal_tube.repository.FactoryInternalTubeRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryInternalTubeService {

    private final FactoryInternalTubeRepository repository;

    public FactoryInternalTubeService(FactoryInternalTubeRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(FactoryInternalTubeDetails details){
        var entity = new FactoryInternalTube(null, "suporte da esfera",
                 details.materialType(), "");

        repository.save(entity);
    }
}
