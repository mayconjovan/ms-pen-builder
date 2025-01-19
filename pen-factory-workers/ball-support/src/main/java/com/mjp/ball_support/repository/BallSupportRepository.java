package com.mjp.ball_support.repository;

import com.mjp.ball_support.entities.BallSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BallSupportRepository extends JpaRepository<BallSupport, UUID> {



}
