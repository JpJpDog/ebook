package org.reins.demo.service.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.dao.UserDao;
import org.reins.demo.entity.CartItemE;
import org.reins.demo.entity.UserE;
import org.reins.demo.model.Book;
import org.reins.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Optional;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer payBooks(List<CartItemE> cartItemES, Integer userId) {
        try {
            Optional<UserE> buyerOptional = userDao.findByUserId(userId);
            if (buyerOptional.isEmpty()) {
                throw new Exception();
            }
            UserE buyer = buyerOptional.get();
            Integer allMoney = buyer.getMoney();
            Integer paySum = 0;
            for (CartItemE cartItemE : cartItemES) {
                Book book = bookDao.findByIdE(cartItemE.getBookId());
                if (book.getStock() < cartItemE.getNum()) {
                    throw new Exception();
                }
                book.setStock(book.getStock() - cartItemE.getNum());
                paySum += cartItemE.getNum() * book.getPrice();
                if (paySum > allMoney) {
                    throw new Exception();
                }
                bookDao.saveBookE(book);
            }
            buyer.setMoney(allMoney - paySum);
            userDao.saveUser(buyer);
            return 0;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
    }
}
