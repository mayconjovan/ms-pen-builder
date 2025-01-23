package com.mjp.factory_ball.repository;

import com.mjp.factory_ball.entities.FactoryBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryBallRepository extends JpaRepository<FactoryBall, UUID> {



}
