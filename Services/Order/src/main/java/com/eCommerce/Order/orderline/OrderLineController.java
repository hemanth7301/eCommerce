package com.eCommerce.Order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {
    private final OrderLineService service;

    @GetMapping("/order/{orderID}")
    public ResponseEntity<List<OrderLineResponse>>findOrderLinesByOrderID(@PathVariable Integer orderID){
        return ResponseEntity.ok(this.service.findAllByOrderID(orderID));
    }
}
