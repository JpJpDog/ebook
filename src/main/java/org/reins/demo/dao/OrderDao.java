package org.reins.demo.dao;

import org.reins.demo.entity.CartItemE;
import org.reins.demo.entity.OrderItemE;
import org.reins.demo.message.OrderMsg;
import org.reins.demo.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    void setOrderPay(Order order);

    Order findById(Integer orderId);

    List<Order> findAllByUserId(Integer userId);

    Integer addOrder(OrderMsg orderMsg);
}
