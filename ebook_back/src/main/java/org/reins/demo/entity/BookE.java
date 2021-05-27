package org.reins.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.reins.demo.model.Book;

import javax.persistence.*;

@Data
@Table(name = "book")
@Entity
@NoArgsConstructor
public class BookE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer price;
    private Integer stock;

    public BookE(Book book) {
        name = book.getBookName();
        price = book.getPrice();
        stock = book.getStock();
    }
}
