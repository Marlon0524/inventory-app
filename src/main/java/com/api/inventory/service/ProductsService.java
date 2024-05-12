package com.api.inventory.service;

import com.api.inventory.model.Products;
import com.api.inventory.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    public Products createProducts(Products products) {
        try {
            // Guardar el producto en la base de datos
            return productsRepository.save(products);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            throw new RuntimeException("Error al crear el producto", e);
        }
    }
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Products> getProductById(Integer id) {
        return productsRepository.findById(id);
    }

    public Optional<Products> updateProduct(Integer productId, Products updatedProduct) {
        Optional<Products> optionalProduct = productsRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Products existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            Products savedProduct = productsRepository.save(existingProduct);
            return Optional.of(savedProduct);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteProduct(Integer productId) {
        Optional<Products> optionalProduct = productsRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            productsRepository.delete(optionalProduct.get());
            return true;
        } else {
            return false;
        }
    }
}
