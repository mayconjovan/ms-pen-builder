package com.mjp.factory_external_tube.service;

import com.mjp.factory_external_tube.entities.FactoryExternalTube;
import com.mjp.factory_external_tube.records.FactoryExternalTubeDetails;
import com.mjp.factory_external_tube.repository.FactoryExternalTubeRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryExternalTubeService {

    private final FactoryExternalTubeRepository repository;

    public FactoryExternalTubeService(FactoryExternalTubeRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(FactoryExternalTubeDetails details){
        var entity = new FactoryExternalTube(null, "suporte da esfera","COLOR", details.materialType());

        repository.save(entity);
    }
}
