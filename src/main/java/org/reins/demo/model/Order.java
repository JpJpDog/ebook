package org.reins.demo.model;

import lombok.Data;
import org.reins.demo.entity.OrderE;
import org.reins.demo.entity.OrderItemE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    Integer id;
    Date date;
    String address;
    List<OrderItem> books;
    Integer userId;
    Boolean hasPay;

    public Order(OrderE orderE, List<Book> books, List<OrderItemE> orderItemES) {
        this.id =orderE.getId();
        this.hasPay = orderE.getHasPay();
        this.userId = orderE.getUserId();
        this.date = orderE.getDate();
        this.address = orderE.getAddress();
        List<OrderItem> orderItems = new ArrayList<>(books.size());
        for (int i = 0; i < books.size(); i++) {
            orderItems.add(new OrderItem(books.get(i), orderItemES.get(i).getNum()));
        }
        this.books = orderItems;
    }
}
