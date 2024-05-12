package com.api.inventory.controller;

import com.api.inventory.model.Products;
import com.api.inventory.repository.ProductsRepository;
import com.api.inventory.service.ProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {
    @Mock
    private ProductsService productsService;
    @InjectMocks
    private ProductsController productsController;
    @Test
    void createProduct() {
        // Arrange
        Products product = new Products();
        when(productsService.createProducts(any())).thenReturn(product);

        // Act
        Products createdProduct = productsController.createProduct(product);

        // Assert
        assertEquals(product, createdProduct);
    }

    @Test
    void getAllProducts() {
        // Arrange
        List<Products> productList = new ArrayList<>();
        when(productsService.getAllProducts()).thenReturn(productList);

        // Act
        List<Products> retrievedProducts = productsController.getAllProducts();

        // Assert
        assertEquals(productList, retrievedProducts);
    }

    @Test
    void getProductById() {
        // Arrange
        Integer productId = 1;
        Products product = new Products();
        when(productsService.getProductById(anyInt())).thenReturn(Optional.of(product));

        // Act
        Products retrievedProduct = productsController.getProductById(productId);

        // Assert
        assertEquals(product, retrievedProduct);
    }

    @Test
    void updateProduct() {
        // Arrange
        Integer productId = 1;
        Products updatedProduct = new Products();
        when(productsService.updateProduct(anyInt(), any())).thenReturn(Optional.of(updatedProduct));

        // Act
        Products updated = productsController.updateProduct(productId, updatedProduct);

        // Assert
        assertEquals(updatedProduct, updated);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Integer productId = 1;
        when(productsService.deleteProduct(anyInt())).thenReturn(true);

        // Act
        productsController.deleteProduct(productId);
        // No hay nada que Assert ya que esperamos que no arroje excepción si el producto se elimina correctamente
    }
}