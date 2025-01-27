package com.mjp.pen_payment_validator.consumer;

import com.mjp.pen_payment_validator.service.PaymentValidatorService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class SqsConsumer {

    private final PaymentValidatorService service;

    public SqsConsumer(PaymentValidatorService service) {
        this.service = service;
    }

    @SqsListener("${aws.sqs.queue}")
    public void listen(String message) {
        service.validatePayment(Integer.parseInt(message));
    }

}
