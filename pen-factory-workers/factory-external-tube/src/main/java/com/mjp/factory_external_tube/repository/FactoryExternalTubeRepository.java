package com.mjp.factory_external_tube.repository;

import com.mjp.factory_external_tube.entities.FactoryExternalTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryExternalTubeRepository extends JpaRepository<FactoryExternalTube, UUID> {



}
