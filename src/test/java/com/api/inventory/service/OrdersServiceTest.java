package com.api.inventory.service;

import com.api.inventory.model.Orders;
import com.api.inventory.repository.OrderRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrders() {
        // arreglo
        OrderRepository orderRepository = mock(OrderRepository.class);
        OrderService orderService = new OrderService(orderRepository);
        Orders mockOrders = new Orders(); // Mocking the order object
        Orders savedOrders = new Orders(); // Mocking the saved order object

        // Mocking del método del repositorio para devolver el pedido guardado
        when(orderRepository.save(any())).thenReturn(savedOrders);

        // Accion
        Orders createdOrders = orderService.createOrders(mockOrders);

        // afirmacion
        assertNotNull(createdOrders);
        assertEquals(savedOrders, createdOrders);
    }

    @Test
    void getAllOrders() {
        // arreglo
        List<Orders> expectedOrders = new ArrayList<>();
        Orders orders1 = new Orders();
        orders1.setOrder_id(1);
        orders1.setName("pedido 1");

        expectedOrders.add(orders1);

        Orders orders2 = new Orders();
        orders2.setOrder_id(2);
        orders2.setName("pedido 2");

        expectedOrders.add(orders2);

        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Accion
        List<Orders> actualOrders = orderService.getAllOrders();

        // afirmacion
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void getOrderById() {
        // arreglo
        Integer orderId = 1;
        Orders expectedOrders = new Orders();
        expectedOrders.setOrder_id(orderId);
        expectedOrders.setName("pedido 1");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrders));

        // accion
        Optional<Orders> actualOrder = orderService.getOrderById(orderId);

        // afirmacion
        assertEquals(Optional.of(expectedOrders), actualOrder);
    }

    @Test
    void updateOrder() {
        // Arrange
        Integer orderId = 1;
        Orders existingOrders = new Orders();
        existingOrders.setOrder_id(orderId);
        existingOrders.setName("Nombre original");


        Orders updatedOrders = new Orders();
        updatedOrders.setName("Nombre modificado");


        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrders));
        when(orderRepository.save(any(Orders.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Orders> optionalUpdatedOrder = orderService.updateOrder(orderId, updatedOrders);

        // Assert
        assertTrue(optionalUpdatedOrder.isPresent());
        Orders actualUpdatedOrders = optionalUpdatedOrder.get();
        assertEquals(updatedOrders.getName(), actualUpdatedOrders.getName());

    }
}