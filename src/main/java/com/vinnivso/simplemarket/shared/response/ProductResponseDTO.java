package com.vinnivso.simplemarket.shared.response;

import com.vinnivso.simplemarket.model.Product;
import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, String brand, Integer quantity, Double price) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getBrand(), product.getQuantity(), product.getPrice());
    }
}
