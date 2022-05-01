package org.hy.pizza.service;

import org.hy.pizza.dto.OrderCreateRequest;
import org.hy.pizza.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    Order create(OrderCreateRequest request);
    void deleteById(Long id);
}
