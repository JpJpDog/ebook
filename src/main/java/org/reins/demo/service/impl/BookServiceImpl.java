package org.reins.demo.service.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.entity.CartItemE;
import org.reins.demo.model.Book;
import org.reins.demo.service.BookService;
import org.reins.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private PayService payService;

    @Override
    public List<Book> findBook(String bookname) {
        return bookDao.findByNameLike(bookname);
    }

    @Override
    public Integer buyBook(Integer userId, Integer bookId, Integer num) {
        List<CartItemE> cartItemES = new ArrayList<>();
        cartItemES.add(new CartItemE(bookId, num, new Date()));
        return payService.payBooks(cartItemES, userId);
    }
}
