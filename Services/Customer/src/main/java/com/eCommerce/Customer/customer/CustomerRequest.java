package com.eCommerce.Customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(String id,
                              @NotBlank(message = "FirstName is required") String firstName,
                              @NotBlank(message = "LastName is required") String lastName,
                              @NotBlank(message = "Email is required") @Email(message = "Enter a valid email") String email,
                              Address address) {

}
