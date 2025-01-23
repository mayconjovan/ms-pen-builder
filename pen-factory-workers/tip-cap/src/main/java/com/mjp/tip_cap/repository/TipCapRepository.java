package com.mjp.tip_cap.repository;

import com.mjp.tip_cap.entities.TipCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipCapRepository extends JpaRepository<TipCap, UUID> {



}
