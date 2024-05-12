package com.api.inventory.controller;

import com.api.inventory.model.Orders;
import com.api.inventory.model.Products;
import com.api.inventory.repository.OrderRepository;
import com.api.inventory.repository.ProductsRepository;
import com.api.inventory.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private ProductsRepository productsRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderController orderController;
    @Test
    void createOrder() {
        // Arrange
        Orders order = new Orders();
        order.setName("Nueva orden");
        order.setState("CONFIRMADA");

        Products product1 = new Products();
        product1.setProduct_id(1);
        Products product2 = new Products();
        product2.setProduct_id(2);
        List<Products> orderProducts = new ArrayList<>(Arrays.asList(product1, product2));
        order.setProducts(orderProducts);

        Products existingProduct1 = new Products();
        existingProduct1.setProduct_id(1);
        existingProduct1.setName("Producto 1");
        existingProduct1.setPrice(10.0);
        Products existingProduct2 = new Products();
        existingProduct2.setProduct_id(2);
        existingProduct2.setName("Producto 2");
        existingProduct2.setPrice(20.0);

        when(productsRepository.findById(1)).thenReturn(Optional.of(existingProduct1));
        when(productsRepository.findById(2)).thenReturn(Optional.of(existingProduct2));
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Orders savedOrder = orderController.createOrder(order);

        // Assert
        assertEquals(order.getName(), savedOrder.getName());
        assertEquals(order.getState(), savedOrder.getState());
        assertEquals(2, savedOrder.getProducts().size());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void getAllOrders() {
        // Arrange
        List<Orders> ordersList = new ArrayList<>();
        when(orderService.getAllOrders()).thenReturn(ordersList);

        // Act
        List<Orders> retrievedOrders = orderController.getAllOrders();

        // Assert
        assertEquals(ordersList, retrievedOrders);
    }

    @Test
    void getOrderById() {
        // Arrange
        Integer orderId = 1;
        Orders orders = new Orders();
        when(orderService.getOrderById(anyInt())).thenReturn(Optional.of(orders));

        // Act
        Orders retrievedOrders = orderController.getOrderById(orderId);

        // Assert
        assertEquals(orders, retrievedOrders);
    }

    @Test
    void updateOrder() {
        // Arrange
        Integer orderId = 1;
        Orders updatedOrders = new Orders();
        when(orderService.updateOrder(anyInt(), any())).thenReturn(Optional.of(updatedOrders));

        // Act
        Orders updated = orderController.updateOrder(orderId, updatedOrders);

        // Assert
        assertEquals(updatedOrders, updated);
    }
}