package com.mjp.pen_payment_validator.service;

import com.mjp.pen_payment_validator.entities.PaymentDetails;

public interface PaymentValidatorStrategy {

    PaymentDetails processPayment(PaymentDetails details);
    String sendNotification();

}
