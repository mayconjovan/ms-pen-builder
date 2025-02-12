package com.mjp.factory.domain.model;

import java.util.UUID;


public class InternalTube {

    private UUID id;
    private String description;
    private String material;
    private String color;
    private Integer orderNumber;

    public InternalTube() {
    }

    public InternalTube(String color, String description, UUID id, String material, Integer orderNumber) {
        this.color = color;
        this.description = description;
        this.id = id;
        this.material = material;
        this.orderNumber = orderNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
