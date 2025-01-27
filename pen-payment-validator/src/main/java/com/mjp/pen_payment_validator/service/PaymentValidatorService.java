package com.mjp.pen_payment_validator.service;

import com.mjp.pen_payment_validator.entities.PaymentDetails;
import com.mjp.pen_payment_validator.producer.SnsPublisher;
import com.mjp.pen_payment_validator.repositories.PaymentValidatorRepository;
import com.mjp.pen_payment_validator.service.strategy.CreditCardStrategy;
import com.mjp.pen_payment_validator.service.strategy.DebitCardStrategy;
import com.mjp.pen_payment_validator.service.strategy.PixStrategy;
import com.mjp.pen_payment_validator.service.strategy.TicketStrategy;
import com.mjp.pen_payment_validator.types.PaymentType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentValidatorService {
    private final PaymentValidatorRepository repository;
    private final SnsPublisher snsPublisher;


    private final Map<PaymentType, PaymentValidatorStrategy> mapStrategy = Map.of(
            PaymentType.CREDIT_CARD, new CreditCardStrategy(),
            PaymentType.DEBIT_CARD, new DebitCardStrategy(),
            PaymentType.PIX, new PixStrategy(),
            PaymentType.TICKET, new TicketStrategy(),
            PaymentType.TRANSFER, new TicketStrategy()
    );

    public PaymentValidatorService(PaymentValidatorRepository repository, SnsPublisher snsPublisher) {
        this.repository = repository;
        this.snsPublisher = snsPublisher;
    }

    public void validatePayment(Long orderNumber) {
        PaymentDetails paymentDetails = repository.findByOrderNumber(orderNumber);

        try {
            var paymentDetailsUpdated = mapStrategy.get(paymentDetails.getPaymentType()).processPayment(paymentDetails);
            repository.save(paymentDetailsUpdated);
            notifyPaymentStatus(orderNumber);
        } catch (Exception e) {
            throw new RuntimeException("Algo errado aconteceu ao processar o pagamento", e);
        }
    }

    private void notifyPaymentStatus(Long orderNumber){
        snsPublisher.publishMessage(orderNumber);
    }

}
