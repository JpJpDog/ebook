package org.reins.demo.service;

import org.reins.demo.kafka_msg.OrderMsg;
import org.reins.demo.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(Integer userId);

    Integer addOrder(OrderMsg msg);

    Integer payOrder(Integer orderId);
}
