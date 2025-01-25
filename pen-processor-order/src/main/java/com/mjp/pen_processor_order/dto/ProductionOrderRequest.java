package com.mjp.pen_processor_order.dto;

public record ProductionOrderRequest(PenDTO penDetails, Integer quantity) {
}
