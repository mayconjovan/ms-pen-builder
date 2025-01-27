package com.mjp.pen_payment_validator.entities;

import com.mjp.pen_payment_validator.types.PaymentStatusType;
import com.mjp.pen_payment_validator.types.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_payment_details")
public class PaymentDetails {

    @Id
    private UUID id;
    private Long orderNumber;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentStatusType paymentStatusType;
    private String cardNumber;
    private Integer validationCardCode;
    private String cardValidity;
    private String cardName;
    private String cardFlag;

}
