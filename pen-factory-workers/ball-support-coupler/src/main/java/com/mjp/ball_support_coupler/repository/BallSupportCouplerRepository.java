package com.mjp.ball_support_coupler.repository;

import com.mjp.ball_support_coupler.entities.BallSupportCoupler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BallSupportCouplerRepository extends JpaRepository<BallSupportCoupler, UUID> {



}
