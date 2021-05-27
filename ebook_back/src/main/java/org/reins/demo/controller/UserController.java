package org.reins.demo.controller;

import org.reins.demo.service.UserService;
import org.reins.demo.socket_msg.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    Integer register(String username, String password) {
        return userService.addUser(username, password);
    }

    @PostMapping("/logIn")
    Integer logIn(@RequestBody Map<String, String> params) {
        String userName = params.get("userName"), password = params.get("password");
        return userService.logIn(userName, password);
    }

    @PostMapping("/setMoney")
    Integer setMoney(@RequestBody Map<String, String> params) {
        return userService.setMoney(Integer.parseInt(params.get("userId")), Integer.parseInt(params.get("money")));
    }
}
