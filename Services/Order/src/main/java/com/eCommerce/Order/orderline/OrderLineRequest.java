package com.eCommerce.Order.orderline;

public record OrderLineRequest(Integer id,
                               Integer orderId,
                               Integer productID,
                               double quantity) {
}
