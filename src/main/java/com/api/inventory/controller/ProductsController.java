package com.api.inventory.controller;

import com.api.inventory.model.Products;
import com.api.inventory.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;

    @PostMapping
    public Products createProduct(@RequestBody Products products) {
        return productsService.createProducts(products);
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Products getProductById(@PathVariable Integer id) {
        try {
            return productsService.getProductById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con el id: " + id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Products updateProduct(@PathVariable Integer id, @RequestBody Products updatedProduct) {
        try {
            return productsService.updateProduct(id, updatedProduct)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con el id: " + id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        try {
            if (!productsService.deleteProduct(id)) {
                throw new RuntimeException("Producto no encontrado con el id: " + id);
            }
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
