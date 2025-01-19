package com.mjp.ink.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_ink")
public class Ink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private Double size;
    private String materialType;

    public Ink() {
    }

    public Ink(UUID id, String description, Double size, String materialType) {
        this.id = id;
        this.description = description;
        this.size = size;
        this.materialType = materialType;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}
