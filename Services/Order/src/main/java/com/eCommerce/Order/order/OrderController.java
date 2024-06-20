package com.eCommerce.Order.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(this.service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>>findAllOrders(){
        return ResponseEntity.ok(this.service.finaAllOrders());
    }

    @GetMapping("/{orderID}")
    public ResponseEntity<OrderResponse>findOrderByID(@PathVariable Integer orderID){
        return ResponseEntity.ok(this.service.findByID(orderID));
    }

}
