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
    private String color;

    public Ink() {
    }

    public Ink(UUID id, String description, String materialType) {
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
