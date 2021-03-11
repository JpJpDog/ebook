package org.reins.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.reins.demo.entity.OrderItemE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
class OrderItem {
    private Book book;
    private Integer num;
}

@Data
public class Order {
    Date date;
    String address;
    List<OrderItem> books;

    public Order(Date date, String address, List<Book> books, List<OrderItemE> orderItemES) {
        this.date = date;
        this.address = address;
        List<OrderItem> orderItems = new ArrayList<>(books.size());
        for (int i = 0; i < books.size(); i++) {
            orderItems.add(new OrderItem(books.get(i), orderItemES.get(i).getNum()));
        }
        this.books = orderItems;
    }
}
