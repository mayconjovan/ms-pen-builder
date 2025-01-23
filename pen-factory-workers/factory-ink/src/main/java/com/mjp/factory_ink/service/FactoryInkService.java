package com.mjp.factory_ink.service;

import com.mjp.factory_ink.entities.FactoryInk;
import com.mjp.factory_ink.records.FactoryInkDetails;
import com.mjp.factory_ink.repository.FactoryInkRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryInkService {

    private final FactoryInkRepository repository;

    public FactoryInkService(FactoryInkRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(FactoryInkDetails details){
        var entity = new FactoryInk(null, "suporte da esfera", details.materialType());

        repository.save(entity);
    }
}
