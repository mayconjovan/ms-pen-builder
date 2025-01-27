package com.mjp.pen_payment_validator.util;

import com.mjp.pen_payment_validator.types.PaymentStatusType;

import java.util.Random;

public class PaymentStatusRandomizer {

    private static final Random RANDOM = new Random();

    public static PaymentStatusType randomStatus() {
        PaymentStatusType[] statuses = PaymentStatusType.values();
        int randomIndex = RANDOM.nextInt(statuses.length);
        return statuses[randomIndex];
    }
}
