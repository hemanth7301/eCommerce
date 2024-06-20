package com.eCommerce.Customer.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper
{
    public Customer toCustomer(CustomerRequest request){
        if(request==null)return null;
        return new Customer(request.id(),
                request.firstName(), request.lastName(),
                request.email(), request.address());
    }

    public CustomerResponse fromCustomer(Customer customer){
        if(customer==null)return null;
        return new CustomerResponse(customer.getId(),
                customer.getFirstName(),customer.getLastName(),
                customer.getEmail(), customer.getAddress());
    }
}
