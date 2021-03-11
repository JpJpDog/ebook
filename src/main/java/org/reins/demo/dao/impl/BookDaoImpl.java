package org.reins.demo.dao.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.entity.BookE;
import org.reins.demo.entity.BookMongo;
import org.reins.demo.model.Book;
import org.reins.demo.repository.BookMongoRepository;
import org.reins.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookMongoRepository bookMongoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findById(Integer bookId) {
        Optional<BookE> bookE = bookRepository.findById(bookId);
        if (bookE.isEmpty()) return null;
        Optional<BookMongo> bookMongo = bookMongoRepository.findByBookId(bookId);
        return new Book(bookE.get(), bookMongo);
    }

    @Override
    public Book findByIdE(Integer bookId) {
        Optional<BookE> bookE = bookRepository.findById(bookId);
        if (bookE.isEmpty()) return null;
        return new Book(bookE.get(), Optional.empty());
    }

    @Override
    public void saveBookE(Book book) {
        BookE bookE = new BookE(book);
        bookRepository.save(bookE);
    }

    @Override
    public List<Book> findByNameLike(String name) {
        List<BookE> bookES = bookRepository.findAllByNameContaining(name);
        List<Book> books = new ArrayList<>();
        for (BookE bookE : bookES) {
            books.add(new Book(bookE, bookMongoRepository.findByBookId(bookE.getId())));
        }
        return books;
    }

    @Override
    public Integer addBook(Book book) {
        BookE bookE = new BookE(book);
        Integer newId = bookRepository.save(bookE).getId();
        BookMongo bookMongo = new BookMongo();
        bookMongo.setBookId(newId);
        bookMongo.setDescription(book.getDescription());
        bookMongoRepository.save(bookMongo);
        return newId;
    }

}
