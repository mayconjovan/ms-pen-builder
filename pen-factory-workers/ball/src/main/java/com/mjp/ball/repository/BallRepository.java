package com.mjp.ball.repository;

import com.mjp.ball.entities.Ball;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BallRepository extends JpaRepository<Ball, UUID> {



}
