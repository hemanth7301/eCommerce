package com.eCommerce.Order.product;

import java.math.BigDecimal;

public record PurchaseResponse(Integer productID,
                               String name,
                               String description,
                               BigDecimal price,
                               double quantity) {
}
