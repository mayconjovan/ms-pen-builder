package com.mjp.pen_processor_order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record OrderProcessDTO(Integer orderNumber,
                              @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
                              Instant orderCreatedAt,
                              Double totalValue,
                              Integer quantity,
                              PenDTO pen) {
}
