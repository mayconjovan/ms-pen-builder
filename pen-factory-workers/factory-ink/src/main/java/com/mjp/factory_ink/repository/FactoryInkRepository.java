package com.mjp.factory_ink.repository;

import com.mjp.factory_ink.entities.FactoryInk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FactoryInkRepository extends JpaRepository<FactoryInk, UUID> {



}
