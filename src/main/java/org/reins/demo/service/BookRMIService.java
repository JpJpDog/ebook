package org.reins.demo.service;

import org.reins.demo.model.Book;

import java.util.List;

public interface BookRMIService {
    List<Book> findBook(String bookname);
}
