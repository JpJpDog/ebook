package org.reins.demo.dao;

import org.reins.demo.model.Book;

import java.util.List;

public interface BookDao {
    Book findById(Integer bookId);

    Book findByIdE(Integer bookId);

    void saveBookE(Book book);

    List<Book> findByNameLike(String name);
}
