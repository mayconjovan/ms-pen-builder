package com.mjp.factory_ball.repository;

import com.mjp.factory_ball.entities.Ball;
import com.mjp.factory_ball.entities.Pen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryPenRepository extends JpaRepository<Pen, UUID> {

}
