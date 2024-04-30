package com.vinnivso.simplemarket.model;

import com.vinnivso.simplemarket.shared.request.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "brand")
    private String brand;

    @Setter
    @Column(name = "quantity")
    private Integer quantity;

    @Setter
    @Column(name = "price")
    private Double price;

    public Product(ProductRequestDTO input) {
        this.name = input.name();
        this.brand = input.brand();
        this.quantity = input.quantity();
        this.price = input.price();
    }
}
