package com.mjp.pen_processor_order.repositories;

import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.types.PaymentStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderProcess, UUID> {

    List<OrderProcess> findAllOrderProcessByPaymentStatusType(PaymentStatusType paymentStatusType);
}
