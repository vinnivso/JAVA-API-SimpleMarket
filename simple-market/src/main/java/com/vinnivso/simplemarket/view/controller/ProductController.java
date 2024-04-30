package com.vinnivso.simplemarket.view.controller;

import com.vinnivso.simplemarket.service.ProductServiceImplementation;
import com.vinnivso.simplemarket.shared.request.ProductRequestDTO;
import com.vinnivso.simplemarket.shared.response.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductServiceImplementation service;

    public ProductController(ProductServiceImplementation service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDTO input) {
        String response = service.createProduct(input);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> response = service.getAllProducts();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID id) {
        ProductResponseDTO response = service.getProduct(id).get();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id, @RequestBody ProductRequestDTO input) {
        ProductResponseDTO response = service.updateProduct(id, input);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        String response = service.deleteProduct(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
