package com.mjp.external_tube.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_external_tube")
public class ExternalTube {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String color;
    private String materialType;

    public ExternalTube() {
    }

    public ExternalTube(UUID id, String description, String color, String materialType) {
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
