package com.vinnivso.simplemarket.service;

import com.vinnivso.simplemarket.shared.request.ProductRequestDTO;
import com.vinnivso.simplemarket.shared.response.ProductResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    String createProduct(ProductRequestDTO dto);

    List<ProductResponseDTO> getAllProducts();

    Optional<ProductResponseDTO> getProduct(UUID id);

    ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto);

    String deleteProduct(UUID id);
}
