package com.mjp.pen_processor_order.entities;

import com.mjp.pen_processor_order.dto.PenDTO;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_pen")
public class Pen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant createdAt;
    private String status;

    @Transient
    private PenDTO penDTO;




    @OneToOne
    @JoinColumn(name = "order_process_pen_id")
    private OrderProcess orderProcess;

    public Pen() {
    }

    public Pen(UUID id, Instant createdAt, String status) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PenDTO getPenDTO() {
        return penDTO;
    }

    public void setPenDTO(PenDTO penDTO) {
        this.penDTO = penDTO;
    }

    public OrderProcess getOrderProcess() {
        return orderProcess;
    }

    public void setOrderProcess(OrderProcess orderProcess) {
        this.orderProcess = orderProcess;
    }
}
