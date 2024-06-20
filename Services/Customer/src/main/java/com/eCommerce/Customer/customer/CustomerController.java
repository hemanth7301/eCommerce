package com.eCommerce.Customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(this.service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        this.service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>>findAllCustomers(){
        return ResponseEntity.ok(this.service.findAllCustomers());
    }

    @GetMapping("/exists/{customerID}")
    public ResponseEntity<Boolean>existsById(@PathVariable String customerID){
        return ResponseEntity.ok(this.service.existsById(customerID));
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<CustomerResponse>findCustomerById(@PathVariable String customerID){
        return ResponseEntity.ok(this.service.findById(customerID));
    }

    @DeleteMapping("/{customerID}")
    public ResponseEntity<Void>deleteCustomer(@PathVariable String customerID){
        this.service.deleteCustomer(customerID);
        return ResponseEntity.accepted().build();
    }
}
