package com.mjp.internal_tube.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_internal_tube")
public class InternalTube {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String materialType;
    private String color;

    public InternalTube() {
    }

    public InternalTube(UUID id, String description, String materialType, String color) {
        this.id = id;
        this.description = description;
        this.materialType = materialType;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
