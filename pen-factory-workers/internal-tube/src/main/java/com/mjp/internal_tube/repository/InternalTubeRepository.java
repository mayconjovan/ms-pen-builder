package com.mjp.internal_tube.repository;

import com.mjp.internal_tube.entities.InternalTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InternalTubeRepository extends JpaRepository<InternalTube, UUID> {



}
