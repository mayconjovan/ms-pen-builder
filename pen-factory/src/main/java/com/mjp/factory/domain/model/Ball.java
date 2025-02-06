package com.mjp.factory.domain.model;

import java.util.UUID;


public class Ball {

    private UUID id;
    private String description;
    private Double size;
    private String material;
    private Integer orderNumber;

    public Ball() {
    }

    public Ball(String description, UUID id, String material, Integer orderNumber, Double size) {
        this.description = description;
        this.id = id;
        this.material = material;
        this.orderNumber = orderNumber;
        this.size = size;
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

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
