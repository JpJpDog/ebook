package org.reins.demo.service.impl;

import org.reins.demo.dao.OrderDao;
import org.reins.demo.kafka_msg.OrderMsg;
import org.reins.demo.model.Order;
import org.reins.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getOrders(Integer userId) {
        return orderDao.findAllByUserId(userId);
    }

    @Override
    public Integer addOrder(OrderMsg msg) {
        return orderDao.addOrder(msg);
    }
}
