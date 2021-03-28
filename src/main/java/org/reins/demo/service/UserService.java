package org.reins.demo.service;

import org.reins.demo.entity.UserE;

public interface UserService {
    Integer logIn(String userName, String password);

    Integer addUser(String username, String password);

    Integer setMoney(Integer userId,Integer money);
}
