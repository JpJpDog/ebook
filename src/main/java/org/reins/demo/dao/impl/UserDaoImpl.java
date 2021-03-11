package org.reins.demo.dao.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.UserE;
import org.reins.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserE> findByUserId(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void saveUser(UserE user) {
        userRepository.save(user);
    }

    @Override
    public Integer addUser(UserE user) {
        UserE new_user = userRepository.save(user);
        return new_user.getId();
    }
}
