package com.mjp.factory.adapters.outbound.sql.jpa.repository;

import com.mjp.factory.adapters.outbound.sql.jpa.entities.JpaPenEntity;
import com.mjp.factory.domain.model.Pen;
import com.mjp.factory.domain.repositories.PenRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class JpaPenRepositoryImpl  implements PenRepository {

    private final JpaPenRepository repository;

    public JpaPenRepositoryImpl(@Lazy JpaPenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAll(List<Pen> pens) {
        var list = pens.stream().map(JpaPenEntity::new).toList();
        repository.saveAll(list);
    }

}
