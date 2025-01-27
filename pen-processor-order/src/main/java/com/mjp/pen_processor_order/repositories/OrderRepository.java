package com.mjp.pen_processor_order.repositories;

import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.types.OrderStatusType;
import com.mjp.pen_processor_order.types.PaymentStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderProcess, UUID> {

    List<OrderProcess> findAllByOrderStatusAndPaymentDetails_PaymentStatusType(OrderStatusType status, PaymentStatusType paymentStatusType);

    Page<OrderProcess> findAllByPaymentDetails_PaymentStatusType(PaymentStatusType paymentStatusType, Pageable pageable);
}
