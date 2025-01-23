package com.mjp.ink.repository;

import com.mjp.ink.entities.Ink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InkRepository extends JpaRepository<Ink, UUID> {



}
