package org.reins.demo.service.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.dao.OrderDao;
import org.reins.demo.dao.UserDao;
import org.reins.demo.model.Book;
import org.reins.demo.model.Order;
import org.reins.demo.model.OrderItem;
import org.reins.demo.model.User;
import org.reins.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer payOrder(Integer orderId) {
        try {
            Order order = orderDao.findById(orderId);
            if (order == null || order.getHasPay()) throw new Exception();
            User buyer = userDao.findByUserId(order.getUserId());
            if (buyer == null) throw new Exception();
            Integer allMoney = buyer.getMoney(), paySum = 0;
            for (OrderItem orderItem : order.getBooks()) {
                Book book = orderItem.getBook();
                if (book == null) throw new Exception();
                if (book.getStock() < orderItem.getNum()) throw new Exception();
                book.setStock(book.getStock() - orderItem.getNum());
                paySum += book.getPrice() * orderItem.getNum();
                if (paySum > allMoney) throw new Exception();
                bookDao.saveBookE(book);
            }
            buyer.setMoney(allMoney - paySum);
            userDao.saveUser(buyer);
            order.setHasPay(true);
            orderDao.setOrderPay(order);
            return 0;
        } catch (Exception e) {
            System.out.println("something error");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
    }
}