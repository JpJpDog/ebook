package org.reins.demo.service;

import org.reins.demo.model.Book;

import java.util.List;

public interface BookService{

    List<Book> findBook(String bookname);

    Integer buyBook(Integer userId, Integer bookId, Integer num, String address);

    Integer addBook(String bookname, Integer price, Integer stock, String description);
}
