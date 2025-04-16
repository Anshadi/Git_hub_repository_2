package com.asthana.Dup_Notification.Records;

import java.math.BigDecimal;

import com.asthana.Dup_Notification.Enums.PaymentMethod;

public record PaymentNotificationRequest(
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String customerFirstName,
    String customerLastName,
    String customerEmail
) {
    
}
