package com.eCommerce.Order.kafka;

import com.eCommerce.Order.customer.CustomerResponse;
import com.eCommerce.Order.payment.PaymentMethod;
import com.eCommerce.Order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(String orderReference,
                                BigDecimal totalAmount,
                                PaymentMethod paymentMethod,
                                CustomerResponse customer,
                                List<PurchaseResponse> products) {
}
