package com.mjp.pen_processor_order.dto;

public record OrderProcessDTO(String numeroPedido, String prazo, Double totalValue, Integer quantity, PenDTO pen) {
}
