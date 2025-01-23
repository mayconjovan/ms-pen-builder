package com.mjp.tip_cap.service;

import com.mjp.tip_cap.entities.TipCap;
import com.mjp.tip_cap.records.TipCapDetails;
import com.mjp.tip_cap.repository.TipCapRepository;
import org.springframework.stereotype.Service;

@Service
public class TipCapService {

    private final TipCapRepository repository;

    public TipCapService(TipCapRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(TipCapDetails details){
        var entity = new TipCap(null, "suporte da esfera",
                details.size(), details.materialType());

        repository.save(entity);
    }
}
