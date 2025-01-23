package com.mjp.factory_outer_tube_cover.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_factory_outer_tube_cover")
public class FactoryOuterTubeCover {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String materialType;
    private String color;

    public FactoryOuterTubeCover() {
    }

    public FactoryOuterTubeCover(UUID id, String description, String color, String materialType) {
        this.id = id;
        this.description = description;
        this.color = color;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
