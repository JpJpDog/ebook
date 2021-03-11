package org.reins.demo.dao.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.UserE;
import org.reins.demo.model.User;
import org.reins.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserId(Integer userId) {
        Optional<UserE> userE = userRepository.findById(userId);
        return userE.map(User::new).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(new UserE(user));
    }

    @Override
    public Integer addUser(User user) {
        return userRepository.save(new UserE(user)).getId();
    }
}
