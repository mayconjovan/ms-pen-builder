package com.mjp.pen_processor_order.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "tb_pen")
@NoArgsConstructor
@AllArgsConstructor
public class Pen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_number", referencedColumnName = "orderNumber")
    private OrderProcess orderProcess;

    @Column(name = "penDetails", columnDefinition = "TEXT")
    private String penDetails;


}