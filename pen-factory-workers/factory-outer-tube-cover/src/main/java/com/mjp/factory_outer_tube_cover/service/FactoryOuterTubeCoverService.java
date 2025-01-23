package com.mjp.factory_outer_tube_cover.service;

import com.mjp.factory_outer_tube_cover.entities.FactoryOuterTubeCover;
import com.mjp.factory_outer_tube_cover.records.FactoryOuterTubeCoverDetails;
import com.mjp.factory_outer_tube_cover.repository.FactoryOuterTubeCoverRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryOuterTubeCoverService {

    private final FactoryOuterTubeCoverRepository repository;

    public FactoryOuterTubeCoverService(FactoryOuterTubeCoverRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(FactoryOuterTubeCoverDetails details){
        var entity = new FactoryOuterTubeCover(null, "suporte da esfera",
               "", details.materialType());

        repository.save(entity);
    }
}
