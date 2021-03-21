package org.reins.demo.controller;

import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    Integer addUser(String username, String password) {
        return userService.addUser(username, password);
    }

    @PostMapping("/register")
    Integer register(String username, String password) {
        return addUser(username, password);
    }
}
