package com.api.inventory.service;

import com.api.inventory.model.Orders;
import com.api.inventory.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Orders createOrders(Orders orders) {
        try {
            // Guardar el pedido en la base de datos
            return orderRepository.save(orders);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            throw new RuntimeException("Error al crear el pedido", e);
        }
    }
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    public Optional<Orders> updateOrder(Integer orderId, Orders updatedOrders) {
        Optional<Orders> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Orders existingOrders = optionalOrder.get();
            existingOrders.setName(updatedOrders.getName());
            existingOrders.setState(updatedOrders.getState());
            existingOrders.setProducts(updatedOrders.getProducts());
            Orders savedOrders = orderRepository.save(existingOrders);
            return Optional.of(savedOrders);
        } else {
            return Optional.empty();
        }
    }
}
