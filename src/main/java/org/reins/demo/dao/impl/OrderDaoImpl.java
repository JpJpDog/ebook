package org.reins.demo.dao.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.dao.OrderDao;
import org.reins.demo.entity.OrderE;
import org.reins.demo.entity.OrderItemE;
import org.reins.demo.model.Book;
import org.reins.demo.model.Order;
import org.reins.demo.repository.OrderItemERepository;
import org.reins.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemERepository orderItemRepository;
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Order> findByUserId(Integer userId) {
        List<Order> orders = new ArrayList<>();
        List<OrderE> orderES = orderRepository.findAllByUserId(userId);
        for (OrderE orderE : orderES) {
            List<OrderItemE> orderItemES = orderItemRepository.findAllByOrderId(orderE.getId());
            List<Book> books = new ArrayList<>();
            for (OrderItemE orderItemE : orderItemES) {
                books.add(bookDao.findById(orderItemE.getBookId()));
            }
            orders.add(new Order(orderE.getDate(), orderE.getAddress(), books, orderItemES));
        }
        return orders;
    }
}
