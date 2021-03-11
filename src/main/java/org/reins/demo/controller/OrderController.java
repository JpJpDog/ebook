package org.reins.demo.controller;

import org.reins.demo.dao.OrderDao;
import org.reins.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderDao orderDao;

    @GetMapping("/all")
    List<Order> getOrders(Integer userId) {
        return orderDao.findAllByUserId(userId);
    }
}
