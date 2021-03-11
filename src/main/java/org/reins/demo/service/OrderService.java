package org.reins.demo.service;

import org.reins.demo.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(Integer userId);
}
