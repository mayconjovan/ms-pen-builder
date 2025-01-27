package com.mjp.pen_payment_validator.util;

import com.mjp.pen_payment_validator.types.PaymentStatusType;

import java.util.Random;

public class PaymentStatusRandomizer {

    private static final Random RANDOM = new Random();

    public static PaymentStatusType randomStatus() {
        PaymentStatusType[] weightedStatuses = {
                PaymentStatusType.APPROVED_PAYMENT,
                PaymentStatusType.APPROVED_PAYMENT,
                PaymentStatusType.APPROVED_PAYMENT,
                PaymentStatusType.APPROVED_PAYMENT,
                PaymentStatusType.FAILED_PAYMENT
        };


        int randomIndex = RANDOM.nextInt(weightedStatuses.length);
        return weightedStatuses[randomIndex];
    }
}
