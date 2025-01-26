package com.mjp.pen_processor_order.dto;

import com.mjp.pen_processor_order.types.PaymentType;

public record PaymentDetailsInput(PaymentType paymentType,
                                  String cardNumber,
                                  String cardValidity,
                                  Integer validationCardCode,
                                  String cardName,
                                  String cardFlag) {
}
