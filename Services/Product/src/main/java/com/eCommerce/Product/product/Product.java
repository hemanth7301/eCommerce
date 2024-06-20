package com.eCommerce.Product.product;

import com.eCommerce.Product.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double availableQuantity;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
