package org.reins.demo.controller;

import org.reins.demo.service.BookService;
import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/init")
public class InitController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public void init() {
        userService.addUser("user1", "111");
        userService.addUser("user2", "222");
        userService.addUser("user3", "333");
        bookService.addBook("book1", 10, 5, "the name is book1");
        bookService.addBook("book2", 10, 5, "the name is book2");
        bookService.addBook("book3", 10, 5, "the name is book3");
    }
}
