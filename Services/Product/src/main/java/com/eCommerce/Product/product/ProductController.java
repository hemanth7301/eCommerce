package com.eCommerce.Product.product;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request){
        return ResponseEntity.ok(this.service.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(this.service.findAllProducts());
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductResponse>getProductById(@PathVariable Integer productID){
        return ResponseEntity.ok(this.service.findById(productID));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>>purchaseProducts(@RequestBody List<ProductPurchaseRequest>request){
        return ResponseEntity.ok(this.service.purchaseProducts(request));
    }
}
