package org.reins.demo.dao.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.entity.BookE;
import org.reins.demo.entity.BookMongo;
import org.reins.demo.model.Book;
import org.reins.demo.repository.BookMongoRepository;
import org.reins.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        List<BookMongo> bookMongos = bookMongoRepository.findByBookId(bookId);
        return new Book(bookE.get(), bookMongos.size() >= 1 ? bookMongos.get(0) : null);
    }

    @Override
    public Book findByIdE(Integer bookId) {
        Optional<BookE> bookE = bookRepository.findById(bookId);
        if (bookE.isEmpty()) return null;
        return new Book(bookE.get(), null);
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
            List<BookMongo> bookMongos = bookMongoRepository.findByBookId(bookE.getId());
            books.add(new Book(bookE, bookMongos.size() >= 1 ? bookMongos.get(0) : null));
        }
        return books;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
