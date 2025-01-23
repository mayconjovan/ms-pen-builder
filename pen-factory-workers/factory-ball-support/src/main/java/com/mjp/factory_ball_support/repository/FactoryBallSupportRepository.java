package com.mjp.factory_ball_support.repository;

import com.mjp.factory_ball_support.entities.FactoryBallSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryBallSupportRepository extends JpaRepository<FactoryBallSupport, UUID> {



}
