package org.reins.demo.dao;

import org.reins.demo.entity.UserE;

import java.util.Optional;

public interface UserDao {
    Optional<UserE> findByUserId(Integer userId);

    void saveUser(UserE user);

    Integer addUser(UserE user);
}
