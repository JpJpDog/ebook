package org.reins.demo.dao;

import org.reins.demo.entity.UserE;
import org.reins.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User findByUserId(Integer userId);

    void saveUser(User user);

    void saveBackUser(User user, Integer userId);

    Integer addUser(User user);

    List<User> findAll();

    User findByUserName(String userName);
}
