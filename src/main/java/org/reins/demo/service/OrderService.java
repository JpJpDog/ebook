package org.reins.demo.service;

import org.reins.demo.entity.CartItemE;
import org.reins.demo.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders(Integer userId);

//    Integer payBooks(List<CartItemE> cartItemES, Integer userId);

    Integer addOrder(Integer userId,String address,List<CartItemE> cartItemES);
}
