package org.reins.demo.service.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.UserE;
import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer addUser(String username, String password) {
        UserE newUser = new UserE();
        newUser.setMoney(100);
        newUser.setName(username);
        newUser.setPassword(password);
        return userDao.addUser(newUser);
    }
}
