package com.mjp.factory.adapters.outbound.sql.jpa.repository;

import com.mjp.factory.adapters.outbound.sql.jpa.entities.JpaPenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPenRepository extends JpaRepository<JpaPenEntity, UUID> {

}
