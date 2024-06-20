package com.eCommerce.Product.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Description is required")
        String description,
        @Positive(message = "Price should be positive")
        BigDecimal price,
        @Positive(message = "Quantity should be positive")
        double availableQuantity,
        @Positive(message = "Product category is required")
        Integer categoryId
) {
}
