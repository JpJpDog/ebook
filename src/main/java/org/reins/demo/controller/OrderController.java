package org.reins.demo.controller;

import org.reins.demo.dao.OrderDao;
import org.reins.demo.model.Order;
import org.reins.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    List<Order> getOrders(Integer userId) {
        return orderService.getOrders(userId);
    }

    @GetMapping("/pay")
    Integer payOrder(Integer orderId) {
        return orderService.payOrder(orderId);
    }
}
