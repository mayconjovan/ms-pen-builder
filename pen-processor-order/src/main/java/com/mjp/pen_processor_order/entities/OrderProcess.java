package com.mjp.pen_processor_order.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_order_process")
public class OrderProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant orderCreatedAt;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;
    private Double totalValue;
    private Integer quantity;

    public OrderProcess() {
    }

    public OrderProcess(UUID id, Instant orderCreated, Integer orderNumber, Integer quantity) {
        this.id = id;
        this.orderCreatedAt = orderCreated;
        this.orderNumber = orderNumber;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getOrderCreatedAt() {
        return orderCreatedAt;
    }

    public void setOrderCreatedAt(Instant orderCreatedAt) {
        this.orderCreatedAt = orderCreatedAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
