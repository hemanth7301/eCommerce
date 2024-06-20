package com.eCommerce.Payment.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer (
        String id,
        @NotBlank(message = "Firstname is required")
        String firstName,
        @NotBlank(message = "Lastname is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "The customer email is not correctly formatted")
        String email
){
}
