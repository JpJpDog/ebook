package org.reins.demo.service.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.dao.OrderDao;
import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.CartItemE;
import org.reins.demo.model.Order;
import org.reins.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> getOrders(Integer userId) {
        return orderDao.findAllByUserId(userId);
    }

    @Override
    public Integer addOrder(Integer userId, String address, List<CartItemE> cartItemES) {
        return orderDao.addOrder(userId, address, cartItemES);
    }
}
