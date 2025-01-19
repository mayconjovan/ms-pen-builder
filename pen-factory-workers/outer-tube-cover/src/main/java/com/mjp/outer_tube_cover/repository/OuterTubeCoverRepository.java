package com.mjp.outer_tube_cover.repository;

import com.mjp.outer_tube_cover.entities.OuterTubeCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OuterTubeCoverRepository extends JpaRepository<OuterTubeCover, UUID> {



}
