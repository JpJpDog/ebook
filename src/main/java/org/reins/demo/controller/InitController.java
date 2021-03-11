package org.reins.demo.controller;

import org.reins.demo.entity.CartItemE;
import org.reins.demo.message.OrderItemMsg;
import org.reins.demo.message.OrderMsg;
import org.reins.demo.model.Order;
import org.reins.demo.service.BookService;
import org.reins.demo.service.OrderService;
import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/init")
public class InitController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public void init() {
        userService.addUser("user1", "111");
        userService.addUser("user2", "222");
        userService.addUser("user3", "333");
        bookService.addBook("book1", 10, 5, "the name is book1");
        bookService.addBook("book2", 10, 5, "the name is book2");
        bookService.addBook("book3", 10, 5, "the name is book3");
        List<OrderItemMsg> orderItemMsgs = new ArrayList<>();
        orderItemMsgs.add(new OrderItemMsg(1, 1));
        orderItemMsgs.add(new OrderItemMsg(2, 2));
        OrderMsg msg = new OrderMsg(1, "China", orderItemMsgs);
        orderService.addOrder(msg);
    }
}
