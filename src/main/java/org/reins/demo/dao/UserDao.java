package org.reins.demo.dao;

import org.reins.demo.entity.UserE;
import org.reins.demo.model.User;

import java.util.Optional;

public interface UserDao {
    User findByUserId(Integer userId);

    void saveUser(User user);

    Integer addUser(User user);

}
