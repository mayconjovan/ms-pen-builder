package com.mjp.factory_tip_cap.service;

import com.mjp.factory_tip_cap.entities.FactoryTipCap;
import com.mjp.factory_tip_cap.records.FactoryTipCapDetails;
import com.mjp.factory_tip_cap.repository.FactoryTipCapRepository;
import org.springframework.stereotype.Service;

@Service
public class FactoryTipCapService {

    private final FactoryTipCapRepository repository;

    public FactoryTipCapService(FactoryTipCapRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(FactoryTipCapDetails details){
        var entity = new FactoryTipCap(null, "suporte da esfera",
                details.size(), details.materialType());

        repository.save(entity);
    }
}
