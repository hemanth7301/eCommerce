package com.eCommerce.Payment.kafka;

import com.eCommerce.Payment.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(String orderReference,
                                         BigDecimal amount,
                                         PaymentMethod paymentMethod,
                                         String customerFirstname,
                                         String customerLastname,
                                         String customerEmail) {
}
