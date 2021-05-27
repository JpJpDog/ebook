package org.reins.demo.service.impl;


import org.reins.demo.model.Book;
import org.reins.demo.service.BookRMIService;
import org.reins.demo.service.BookService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRMIServiceImpl implements BookRMIService, ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public List<Book> findBook(String bookname) {
        BookService bookService = applicationContext.getBean(BookService.class);
        return bookService.findBook(bookname);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BookRMIServiceImpl.applicationContext = applicationContext;
    }
}
