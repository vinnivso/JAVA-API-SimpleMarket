package com.vinnivso.simplemarket.service;

import com.vinnivso.simplemarket.model.Product;
import com.vinnivso.simplemarket.model.exception.ResourceNotFoundException;
import com.vinnivso.simplemarket.repository.ProductRepository;
import com.vinnivso.simplemarket.shared.request.ProductRequestDTO;
import com.vinnivso.simplemarket.shared.response.ProductResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
    Logger logger = LogManager.getLogger(ProductServiceImplementation.class);
    @Autowired
    private final ProductRepository repository;

    public ProductServiceImplementation(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new product.
     *
     * @param  dto  the product request DTO
     * @return      the success message
     */
    @Override
    public String createProduct(ProductRequestDTO dto) {
        List<Product> products = repository.findAll();
        for (Product product : products) {
            if (product.getName().equals(dto.name()) && product.getBrand().equals(dto.brand())) {
                logger.error("\u001B[34mProduct not inserted! :" + dto.name() + dto.brand());
                throw new Error("Product already exists");
            }
        }

        Product product = new Product(dto);
        logger.info("\u001B[34mProduct inserted successfully! :" + product.getName());

        repository.save(product);
        return "Product created successfully";
    }

    /**
     * Retrieves all products from the repository and maps them to a list of ProductResponseDTOs.
     *
     * @return  a list of ProductResponseDTOs containing all products
     */
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        logger.info("\u001B[34mSearching all products:" + repository.count());
        List<Product> products = repository.findAll();
        return products.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID and returns it as an Optional wrapped in a ProductResponseDTO.
     *
     * @param  id  the UUID of the product to retrieve
     * @return     an Optional containing the ProductResponseDTO representing the product, or an empty Optional if the product is not found
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public Optional<ProductResponseDTO> getProduct(UUID id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            logger.error("\u001B[34mProduct not found! :" + id);
            throw new ResourceNotFoundException("Product not found");
        }

        logger.info("\u001B[34mProduct found: " + id);
        ProductResponseDTO response = product.stream().map(ProductResponseDTO::new).findFirst().get();
        return Optional.of(response);
    }

    /**
     * Updates a product with the given ID using the provided ProductRequestDTO.
     *
     * @param  id   the UUID of the product to update
     * @param  dto  the ProductRequestDTO containing the updated product information
     * @return      the updated ProductResponseDTO representing the updated product
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO dto) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            logger.error("\u001B[34mProduct not updated! :" + id);
            throw new ResourceNotFoundException("Product not found");
        }

        if (dto.name() != null) {
            product.get().setName(dto.name());
        } else {
            product.get().setName(product.get().getName());
        }

        if (dto.brand() != null) {
            product.get().setBrand(dto.brand());
        } else {
            product.get().setBrand(product.get().getBrand());
        }

        if (dto.quantity() != null) {
            product.get().setQuantity(dto.quantity());
        } else {
            product.get().setQuantity(product.get().getQuantity());
        }

        if (dto.price() != null) {
            product.get().setPrice(dto.price());
        } else {
            product.get().setPrice(product.get().getPrice());
        }

        logger.info("\u001B[34mUpdating product: " + id);
        repository.save(product.get());
        return new ProductResponseDTO(product.get());
    }

    /**
     * Deletes a product with the given ID.
     *
     * @param  id  the UUID of the product to delete
     * @return     a string indicating the success of the deletion
     * @throws ResourceNotFoundException if the product with the given ID is not found
     */
    @Override
    public String deleteProduct(UUID id) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()) {
            logger.error("\u001B[34mProduct not deleted! :" + id);
            throw new ResourceNotFoundException("Product not found");
        }

        logger.info("\u001B[34mRemoving product: " + id);
        repository.deleteById(id);
        return "Product deleted successfully";
    }
}
