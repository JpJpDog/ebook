package org.reins.demo.service.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.UserE;
import org.reins.demo.model.User;
import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Integer addUser(String username, String password) {
        User newUser =new User(username,password,100);
        return userDao.addUser(newUser);
    }
}
