package com.asthana.Dup_Notification.Records;

import java.math.BigDecimal;
import java.util.List;

import com.asthana.Dup_Notification.Enums.PaymentMethod;

public record OrderConfirmation(
    String orderReference,

    BigDecimal totalAmount,

    PaymentMethod paymentMethod,

    Customer customer,
    
    List<Product> products
) {}
