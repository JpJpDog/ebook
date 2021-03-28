package org.reins.demo.service.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.UserE;
import org.reins.demo.model.User;
import org.reins.demo.service.StatisticService;
import org.reins.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    static private class AddVisitNRunnable implements Runnable {
        private final StatisticService service;

        public AddVisitNRunnable(StatisticService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.addVisitNum();
        }
    }

    @Autowired
    private UserDao userDao;
    @Autowired
    private StatisticService statisticService;

    @Override
    public Integer logIn(String userName, String password) {
        User user = userDao.findByUserName(userName);
        if (!user.getPassword().equals(password)) {
            return 1;
        }
        (new Thread(new AddVisitNRunnable(statisticService))).start();
        return 0;
    }

    @Override
    public Integer addUser(String username, String password) {
        User newUser = new User(username, password, 100);
        return userDao.addUser(newUser);
    }

    @Override
    public Integer setMoney(Integer userId, Integer money) {
        User user = userDao.findByUserId(userId);
        user.setMoney(money);
        userDao.saveBackUser(user, userId);
        return 0;
    }
}
