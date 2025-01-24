package com.mjp.pen_processor_order.dto;

import java.time.Instant;

public record OrderProcessDTO(Integer orderNumber, Instant orderCreatedAt, Double totalValue, Integer quantity, PenDTO pen) {
}
