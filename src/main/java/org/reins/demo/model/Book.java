package org.reins.demo.model;

import lombok.Data;
import org.reins.demo.entity.BookE;
import org.reins.demo.entity.BookMongo;

import java.util.Optional;

@Data
public class Book {
    private Integer bookId;
    private String bookName;
    private Integer price;
    private Integer stock;
    private String description;

    public Book(BookE bookE, Optional<BookMongo> bookMongo) {
        bookId = bookE.getId();
        bookName = bookE.getName();
        price = bookE.getPrice();
        stock = bookE.getStock();
        bookMongo.ifPresent(mongo -> description = mongo.getDescription());
    }
}
