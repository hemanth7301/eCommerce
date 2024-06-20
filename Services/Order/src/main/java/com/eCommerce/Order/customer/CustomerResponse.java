package com.eCommerce.Order.customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
