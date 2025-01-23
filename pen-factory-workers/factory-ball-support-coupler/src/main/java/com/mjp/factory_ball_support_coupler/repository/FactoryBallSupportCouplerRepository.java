package com.mjp.factory_ball_support_coupler.repository;

import com.mjp.factory_ball_support_coupler.entities.FactoryBallSupportCoupler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryBallSupportCouplerRepository extends JpaRepository<FactoryBallSupportCoupler, UUID> {



}
