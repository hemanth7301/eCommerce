package com.eCommerce.Notification.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productID,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
