package com.mjp.pen_processor_order.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class EntityManagerHelper {

    @PersistenceContext
    private EntityManager entityManager;


    public Integer getNextSequenceValue(String sequenceName) {
        return ((Number) entityManager.createNativeQuery(
                "SELECT nextval('"+ sequenceName+"')")
                .getSingleResult()).intValue();
    }
}
