package com.mjp.pen_payment_validator.entities;

import com.mjp.pen_payment_validator.types.PaymentStatusType;
import com.mjp.pen_payment_validator.types.PaymentType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

    @Id
    private UUID id;
    private Long orderNumber;
    private PaymentType paymentType;
    private PaymentStatusType paymentStatusType;
    private String cardNumber;
    private Integer validationCardCode;
    private String cardValidity;
    private String cardName;
    private String cardFlag;

}
