package com.mjp.ink.service;

import com.mjp.ink.entities.Ink;
import com.mjp.ink.records.InkDetails;
import com.mjp.ink.repository.InkRepository;
import org.springframework.stereotype.Service;

@Service
public class InkService {

    private final InkRepository repository;

    public InkService(InkRepository repository) {
        this.repository = repository;
    }


    public void createBallSupportCoupler(InkDetails details){
        var entity = new Ink(null, "suporte da esfera",
                details.size(), details.materialType());

        repository.save(entity);
    }
}
