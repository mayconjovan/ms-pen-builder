package com.mjp.outer_tube_cover.service;

import com.mjp.outer_tube_cover.entities.OuterTubeCover;
import com.mjp.outer_tube_cover.records.OuterTubeCoverDetails;
import com.mjp.outer_tube_cover.repository.OuterTubeCoverRepository;
import org.springframework.stereotype.Service;

@Service
public class OuterTubeCoverService {

    private final OuterTubeCoverRepository repository;

    public OuterTubeCoverService(OuterTubeCoverRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(OuterTubeCoverDetails details){
        var entity = new OuterTubeCover(null, "suporte da esfera",
                details.size(), details.materialType());

        repository.save(entity);
    }
}
