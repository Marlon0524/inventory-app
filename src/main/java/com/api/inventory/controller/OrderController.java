package com.api.inventory.controller;

import com.api.inventory.model.Orders;
import com.api.inventory.model.Products;
import com.api.inventory.repository.OrderRepository;
import com.api.inventory.repository.ProductsRepository;
import com.api.inventory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/iasinventory")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final ProductsRepository productsRepository;

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        List<Products> products = new ArrayList<>();
        for (Products product : order.getProducts()) {
            Optional<Products> existingProduct = productsRepository.findById(product.getProduct_id());
            existingProduct.ifPresent(products::add);
        }
        order.setProducts(products);
        return orderRepository.save(order);
    }


    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable Integer id) {
        try {
            return orderService.getOrderById(id)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el id: " + id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable Integer id, @RequestBody Orders updatedOrders) {
        try {
            return orderService.updateOrder(id, updatedOrders)
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el id: " + id));
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
