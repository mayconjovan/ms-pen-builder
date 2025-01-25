package com.mjp.pen_processor_order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mjp.pen_processor_order.entities.OrderProcess;
import com.mjp.pen_processor_order.types.PaymentType;

import java.time.Instant;
import java.util.List;

public record OrderProcessDTO(Integer orderNumber,
                              @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
                              Instant orderCreatedAt,
                              Double totalValue,
                              PaymentType paymentType,
                              List<PensDetails> pen) {

    public static OrderProcessDTO fromEntity(OrderProcess orderProcess, List<PensDetails> penDTO) {
        return new OrderProcessDTO(
                orderProcess.getOrderNumber(),
                orderProcess.getOrderCreatedAt(),
                orderProcess.getTotalValue(),
                orderProcess.getPaymentType(),
                penDTO
        );
    }
}
