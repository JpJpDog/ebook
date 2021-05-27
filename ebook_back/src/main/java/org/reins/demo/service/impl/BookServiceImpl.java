package org.reins.demo.service.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.kafka_msg.OrderItemMsg;
import org.reins.demo.kafka_msg.OrderMsg;
import org.reins.demo.model.Book;
import org.reins.demo.service.BookService;
import org.reins.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private MessageService messageService;

    @Override
    @Transactional
    public List<Book> findBook(String bookname) {
        return bookDao.findByNameLike(bookname);
    }

    @Override
    @Transactional
    public Integer buyBook(Integer userId, Integer bookId, Integer num, String address) {
        List<OrderItemMsg> orderItemMsgs = new ArrayList<>();
        orderItemMsgs.add(new OrderItemMsg(bookId, num));
        System.out.format("### Kafka send book order from userId %d\n", userId);
        return messageService.sendMsg("book_order", new OrderMsg(userId, address, orderItemMsgs));
    }

    @Override
    public Integer addBook(String bookName, Integer price, Integer stock, String description) {
        Book book = new Book(bookName, price, stock, description);
        return bookDao.addBook(book);
    }
}
