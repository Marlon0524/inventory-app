package com.api.inventory.service;


import com.api.inventory.model.Products;
import com.api.inventory.repository.ProductsRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {
    @Mock
    private ProductsRepository productsRepository;
    @InjectMocks
    private ProductsService productsService;
    @Test
    void createProducts() {
        // arreglo
        ProductsRepository productsRepository = mock(ProductsRepository.class);
        ProductsService productsService = new ProductsService(productsRepository);
        Products mockProduct = new Products(); // Mocking the product object
        Products savedProduct = new Products(); // Mocking the saved product object

        // Mocking del método del repositorio para devolver el producto guardado
        when(productsRepository.save(any())).thenReturn(savedProduct);

        // Accion
        Products createdProduct = productsService.createProducts(mockProduct);

        // afirmacion
        assertNotNull(createdProduct);
        assertEquals(savedProduct, createdProduct);
    }
    @Test
    void getAllProducts() {
        // arreglo
        List<Products> expectedProducts = new ArrayList<>();
        Products product1 = new Products();
        product1.setProduct_id(1);
        product1.setName("Product 1");
        product1.setPrice(10.0);
        expectedProducts.add(product1);

        Products product2 = new Products();
        product2.setProduct_id(2);
        product2.setName("Product 2");
        product2.setPrice(20.0);
        expectedProducts.add(product2);

        when(productsRepository.findAll()).thenReturn(expectedProducts);

        // Accion
        List<Products> actualProducts = productsService.getAllProducts();

        // afirmacion
        assertEquals(expectedProducts, actualProducts);
    }
    @Test
    void getProductById(){
        // arreglo
        Integer productId = 1;
        Products expectedProduct = new Products();
        expectedProduct.setProduct_id(productId);
        expectedProduct.setName("Product 1");
        expectedProduct.setPrice(10.0);
        when(productsRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        // accion
        Optional<Products> actualProduct = productsService.getProductById(productId);

        // afirmacion
        assertEquals(Optional.of(expectedProduct), actualProduct);
    }

    @Test
    void updateProduct(){
        // Arrange
        Integer productId = 1;
        Products existingProduct = new Products();
        existingProduct.setProduct_id(productId);
        existingProduct.setName("Nombre original");
        existingProduct.setPrice(10.0);

        Products updatedProduct = new Products();
        updatedProduct.setName("Nombre modificado");
        updatedProduct.setPrice(20.0);

        when(productsRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productsRepository.save(any(Products.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Products> optionalUpdatedProduct = productsService.updateProduct(productId, updatedProduct);

        // Assert
        assertTrue(optionalUpdatedProduct.isPresent());
        Products actualUpdatedProduct = optionalUpdatedProduct.get();
        assertEquals(updatedProduct.getName(), actualUpdatedProduct.getName());
        assertEquals(updatedProduct.getPrice(), actualUpdatedProduct.getPrice());
    }

    @Test
    void deleteProduct(){
        // Arrange
        Integer productId = 1;
        Products existingProduct = new Products();
        existingProduct.setProduct_id(productId);
        existingProduct.setName("Product 1");
        existingProduct.setPrice(10.0);

        when(productsRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        // Act
        boolean deleted = productsService.deleteProduct(productId);

        // Assert
        assertTrue(deleted);
        verify(productsRepository, times(1)).delete(existingProduct);
    }
}