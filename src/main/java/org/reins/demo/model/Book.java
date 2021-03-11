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

    public Book(String bookName, Integer price, Integer stock, String description) {
        this.bookName = bookName;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public Book(BookE bookE, BookMongo bookMongo) {
        bookId = bookE.getId();
        bookName = bookE.getName();
        price = bookE.getPrice();
        stock = bookE.getStock();
        if(bookMongo!=null){
            this.description=bookMongo.getDescription();
        }
    }
}
