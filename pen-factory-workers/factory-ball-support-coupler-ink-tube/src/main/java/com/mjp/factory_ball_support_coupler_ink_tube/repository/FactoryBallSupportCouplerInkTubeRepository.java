package com.mjp.factory_ball_support_coupler_ink_tube.repository;

import com.mjp.factory_ball_support_coupler_ink_tube.entities.FactoryBallSupportCouplerInkTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryBallSupportCouplerInkTubeRepository extends JpaRepository<FactoryBallSupportCouplerInkTube, UUID> {



}
