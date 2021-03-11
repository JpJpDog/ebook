package org.reins.demo.controller;

import org.reins.demo.model.Book;
import org.reins.demo.service.BookRMIService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @GetMapping("/find")
    public List<Book> findBooks(String bookname) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookRMIService bookRMIService = (BookRMIService) ctx.getBean("rmiProxyFactory");
        return bookRMIService.findBook(bookname);
    }
}
