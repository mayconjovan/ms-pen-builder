package com.mjp.pen_processor_order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mjp.pen_processor_order.entities.OrderProcess;

import java.time.Instant;
import java.util.List;

public record OrderProcessDTO(Integer orderNumber,
                              @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
                              Instant orderCreatedAt,
                              Double totalValue,
                              PaymentDetailsOutput paymentDetails,
                              List<PensDetailsDTO> pen) {

    public static OrderProcessDTO fromEntity(OrderProcess orderProcess, List<PensDetailsDTO> penDTO) {
        return new OrderProcessDTO(
                orderProcess.getOrderNumber(),
                orderProcess.getOrderCreatedAt(),
                orderProcess.getTotalValue(),
                new PaymentDetailsOutput(orderProcess.getPaymentDetails().getCardName(),
                        orderProcess.getPaymentDetails().getCardNumber()),
                penDTO
        );
    }
}
