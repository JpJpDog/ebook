package org.reins.demo.controller;

import org.reins.demo.model.Book;
import org.reins.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/find")
    List<Book> findBook(String bookname) {
        return bookService.findBook(bookname);
    }

    @PostMapping(name = "/pay")
    Integer buyBook(Integer userId, Integer bookId, Integer num, String address) {
        return bookService.buyBook(userId, bookId, num, address);
    }
}
