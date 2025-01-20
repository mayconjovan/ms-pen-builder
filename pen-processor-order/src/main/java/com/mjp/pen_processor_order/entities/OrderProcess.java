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
    private Instant orderCreated;

    @OneToOne
    @MapsId
    private Pen pen;

    private Integer quantity;

    public OrderProcess() {
    }

    public OrderProcess(UUID id, Instant orderCreated, Pen pen, Integer quantity) {
        this.id = id;
        this.orderCreated = orderCreated;
        this.pen = pen;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Instant orderCreated) {
        this.orderCreated = orderCreated;
    }

    public Pen getPen() {
        return pen;
    }

    public void setPen(Pen pen) {
        this.pen = pen;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
