package com.mjp.pen_payment_validator.service.strategy;

import com.mjp.pen_payment_validator.entities.PaymentDetails;
import com.mjp.pen_payment_validator.service.PaymentValidatorStrategy;
import com.mjp.pen_payment_validator.types.PaymentStatusType;
import com.mjp.pen_payment_validator.util.PaymentStatusRandomizer;

public class CreditCardStrategy implements PaymentValidatorStrategy {

    @Override
    public PaymentDetails processPayment(PaymentDetails details) {
        System.out.println("Validando pagamento com cartão de credito com integração a terceiros");

        PaymentStatusType randomStatus = PaymentStatusRandomizer.randomStatus();
        details.setPaymentStatusType(randomStatus);

        return details;
    }
}
