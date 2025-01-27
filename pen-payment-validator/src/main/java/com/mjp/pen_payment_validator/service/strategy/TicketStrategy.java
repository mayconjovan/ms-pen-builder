package com.mjp.pen_payment_validator.service.strategy;

import com.mjp.pen_payment_validator.entities.PaymentDetails;
import com.mjp.pen_payment_validator.service.PaymentValidatorStrategy;
import com.mjp.pen_payment_validator.types.PaymentStatusType;
import com.mjp.pen_payment_validator.util.PaymentStatusRandomizer;

public class TicketStrategy implements PaymentValidatorStrategy {

    @Override
    public PaymentDetails processPayment(PaymentDetails details) {
        System.out.println("Gerando boleto");

        //integrar com banco para gerar boleto
        //enviar boleto por e-mail/whatsapp
        PaymentStatusType randomStatus = PaymentStatusRandomizer.randomStatus();
        details.setPaymentStatusType(randomStatus);

        return details;
    }
}
