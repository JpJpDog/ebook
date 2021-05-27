package org.reins.demo.controller;

import org.reins.demo.kafka_msg.OrderItemMsg;
import org.reins.demo.kafka_msg.OrderMsg;
import org.reins.demo.service.BookService;
import org.reins.demo.service.OrderService;
import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        userService.addUser("user2", "111");
        userService.addUser("user3", "111");
        userService.addUser("user4", "111");
        userService.addUser("user5", "111");
        userService.addUser("user6", "111");
        bookService.addBook("book1", 10, 5, "the name is book1");
        bookService.addBook("book2", 10, 5, "the name is book2");
        bookService.addBook("book3", 10, 5, "the name is book3");
        bookService.addBook("book4", 10, 5, "the name is book3");
        bookService.addBook("book5", 10, 5, "the name is book3");
        bookService.addBook("book6", 10, 5, "the name is book3");
//        List<OrderItemMsg> orderItemMsgs = new ArrayList<>();
//        orderItemMsgs.add(new OrderItemMsg(1, 1));
//        orderItemMsgs.add(new OrderItemMsg(2, 2));
//        OrderMsg msg = new OrderMsg(1, "China", orderItemMsgs);
//        orderService.addOrder(msg);
    }
}
