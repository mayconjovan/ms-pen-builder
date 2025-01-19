package com.mjp.coupler_support_ball_ink_tube.repository;

import com.mjp.coupler_support_ball_ink_tube.entities.BallSupportCouplerInkTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BallSupportCouplerInkTubeRepository extends JpaRepository<BallSupportCouplerInkTube, UUID> {



}
