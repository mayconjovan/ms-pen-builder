package com.mjp.pen_processor_order.dto;

import com.mjp.pen_processor_order.entities.PaymentDetails;

import java.util.List;

public record ProductionOrderRequest(List<PensDetailsDTO> pensDetails, PaymentDetailsInput paymentDetails) {
}
