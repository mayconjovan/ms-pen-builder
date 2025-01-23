package com.mjp.factory_internal_tube.repository;

import com.mjp.factory_internal_tube.entities.FactoryInternalTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryInternalTubeRepository extends JpaRepository<FactoryInternalTube, UUID> {



}
