package org.reins.demo.controller;

import org.reins.demo.model.Order;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/all")
    List<Order> getOrders(Integer userId) {
        return null;
    }
}
