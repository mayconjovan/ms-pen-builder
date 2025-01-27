package com.mjp.pen_payment_validator.repositories;

import com.mjp.pen_payment_validator.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentValidatorRepository extends JpaRepository<PaymentDetails, UUID> {

    PaymentDetails findByOrderNumber(Integer orderNumber);
}
