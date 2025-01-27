package com.mjp.pen_processor_order.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjp.pen_processor_order.dto.PaymentDetailsInput;
import com.mjp.pen_processor_order.types.PaymentStatusType;
import com.mjp.pen_processor_order.types.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_payment_details")
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_number", referencedColumnName = "orderNumber")
    private OrderProcess orderProcess;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatusType;

    private String cardNumber;
    private Integer validationCardCode;
    private String cardValidity;
    private String cardName;
    private String cardFlag;

    @PrePersist
    private void prePersist() {
       this.paymentStatusType = PaymentStatusType.WAITING_PAYMENT;
    }

    public PaymentDetails(PaymentDetailsInput paymentDetails, OrderProcess orderProcess){
        this.orderProcess = orderProcess;
        this.paymentType = paymentDetails.paymentType();
        this.cardNumber = paymentDetails.cardNumber();
        this.validationCardCode = paymentDetails.validationCardCode();
        this.cardValidity = paymentDetails.cardValidity();
        this.cardName = paymentDetails.cardName();
        this.cardFlag = paymentDetails.cardFlag();
    }
}