package org.reins.demo.dao.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.UserE;
import org.reins.demo.model.User;
import org.reins.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
    public void saveBackUser(User user, Integer userId) {
        UserE userE = new UserE(user);
        userE.setId(userId);
        userRepository.save(userE);
    }

    @Override
    public Integer addUser(User user) {
        return userRepository.save(new UserE(user)).getId();
    }

    @Override
    public List<User> findAll() {
        List<UserE> userES = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for (UserE userE : userES) {
            users.add(new User(userE));
        }
        return users;
    }

    @Override
    public User findByUserName(String userName) {
        List<UserE> userEList = userRepository.findByName(userName);
        if(userEList.isEmpty()) return null;
        return new User(userEList.get(0));
    }
}
