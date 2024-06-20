package com.eCommerce.Product.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotBlank(message = "Product ID is required")
        Integer productID,
        @Positive(message = "Quantity ID is required")
        Integer quantity

) {
}
