package com.mjp.factory_tip_cap.repository;

import com.mjp.factory_tip_cap.entities.FactoryTipCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryTipCapRepository extends JpaRepository<FactoryTipCap, UUID> {



}
