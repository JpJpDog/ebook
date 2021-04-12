package org.reins.demo.dao.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.dao.OrderDao;
import org.reins.demo.entity.OrderE;
import org.reins.demo.entity.OrderItemE;
import org.reins.demo.kafka_msg.OrderItemMsg;
import org.reins.demo.kafka_msg.OrderMsg;
import org.reins.demo.model.Book;
import org.reins.demo.model.Order;
import org.reins.demo.repository.OrderItemERepository;
import org.reins.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemERepository orderItemRepository;
    @Autowired
    private BookDao bookDao;


    @Override
    public void setOrderPay(Order order) {
        orderRepository.save(new OrderE(order));
    }

    @Override
    public Order findById(Integer orderId) {
        Optional<OrderE> orderEOptional = orderRepository.findById(orderId);
        if (orderEOptional.isEmpty()) return null;
        OrderE orderE = orderEOptional.get();
        List<OrderItemE> orderItemES = orderItemRepository.findAllByOrderId(orderId);
        List<Book> books = new ArrayList<>();
        for (OrderItemE orderItemE : orderItemES) {
            books.add(bookDao.findByIdE(orderItemE.getBookId()));
        }
        return new Order(orderE, books, orderItemES);
    }

    @Override
    public List<Order> findAllByUserId(Integer userId) {
        List<Order> orders = new ArrayList<>();
        List<OrderE> orderES = orderRepository.findAllByUserId(userId);
        for (OrderE orderE : orderES) {
            List<OrderItemE> orderItemES = orderItemRepository.findAllByOrderId(orderE.getId());
            List<Book> books = new ArrayList<>();
            for (OrderItemE orderItemE : orderItemES) {
                books.add(bookDao.findById(orderItemE.getBookId()));
            }
            orders.add(new Order(orderE, books, orderItemES));
        }
        return orders;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addOrder(OrderMsg msg) {
        OrderE orderE = new OrderE(msg.getUserId(), msg.getAddress(), new Date());
        Integer orderId = orderRepository.save(orderE).getId();
        for (OrderItemMsg bookItem : msg.getItems()) {
            OrderItemE orderItemE = new OrderItemE(orderId, bookItem.getBookId(), bookItem.getNum());
            orderItemRepository.save(orderItemE);
        }
        return orderId;
    }

}
