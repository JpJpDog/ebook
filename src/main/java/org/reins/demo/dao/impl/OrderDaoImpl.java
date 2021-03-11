package org.reins.demo.dao.impl;

import org.reins.demo.dao.OrderDao;
import org.reins.demo.entity.OrderE;
import org.reins.demo.entity.OrderItemE;
import org.reins.demo.repository.OrderItemRepository;
import org.reins.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderE> findOrderByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<OrderItemE> findOrderItemByOrderId(Integer bookId) {
        return orderItemRepository.findAllByOrderId(bookId);
    }
}
