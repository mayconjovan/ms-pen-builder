package com.mjp.factory_outer_tube_cover.repository;

import com.mjp.factory_outer_tube_cover.entities.FactoryOuterTubeCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryOuterTubeCoverRepository extends JpaRepository<FactoryOuterTubeCover, UUID> {



}
