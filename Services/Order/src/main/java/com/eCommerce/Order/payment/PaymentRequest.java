package com.eCommerce.Order.payment;

import com.eCommerce.Order.customer.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest (BigDecimal amount,
                              PaymentMethod paymentMethod,
                              Integer orderID,
                              String orderReference,
                              CustomerResponse customer){
}
