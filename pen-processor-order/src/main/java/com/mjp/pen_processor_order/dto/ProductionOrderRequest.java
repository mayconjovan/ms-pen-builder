package com.mjp.pen_processor_order.dto;

import com.mjp.pen_processor_order.types.PaymentType;

import java.util.List;

public record ProductionOrderRequest(List<PensDetails> pensDetails, PaymentType paymentType) {
}
