package com.mjp.factory_ink.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_factory_ink")
public class FactoryInk {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String color;

    public FactoryInk() {
    }

    public FactoryInk(UUID id, String description, String materialType) {
        this.id = id;
        this.description = description;
        this.color = materialType;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
