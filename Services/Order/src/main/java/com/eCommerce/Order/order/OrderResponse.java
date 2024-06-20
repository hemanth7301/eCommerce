package com.eCommerce.Order.order;

import com.eCommerce.Order.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(Integer id,
                            String reference,
                            BigDecimal amount,
                            PaymentMethod paymentMethod,
                            String customerId) {
}
