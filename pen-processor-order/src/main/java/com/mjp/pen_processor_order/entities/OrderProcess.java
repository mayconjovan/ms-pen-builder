package com.mjp.pen_processor_order.entities;

import com.mjp.pen_processor_order.types.PaymentStatusType;
import com.mjp.pen_processor_order.types.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_process")
public class OrderProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant orderCreatedAt;

    @Column(unique = true)
    @SequenceGenerator(
            name = "order_number_gen",
            sequenceName = "order_number_seq",
            allocationSize = 1,
            initialValue = 1
    )
    private Integer orderNumber;
    private Double totalValue;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderProcess", cascade = CascadeType.ALL)
    private List<Pen> pens;

    private PaymentType paymentType;
    private PaymentStatusType paymentStatusType;

    @PrePersist
    private void prePersist() {
        if (orderCreatedAt == null) {
            orderCreatedAt = Instant.now();
        }
        this.paymentStatusType = PaymentStatusType.WAITING_PAYMENT;
    }
}
