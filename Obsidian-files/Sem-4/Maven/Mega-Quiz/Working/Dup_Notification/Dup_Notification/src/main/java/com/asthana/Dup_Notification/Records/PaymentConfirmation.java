package com.asthana.Dup_Notification.Records;

import java.math.BigDecimal;

import com.asthana.Dup_Notification.Enums.PaymentMethod;

public record PaymentConfirmation(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
}
