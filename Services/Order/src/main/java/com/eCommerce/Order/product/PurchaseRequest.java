package com.eCommerce.Order.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productID,
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
