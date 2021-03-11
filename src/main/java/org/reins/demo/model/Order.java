package org.reins.demo.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
class OrderItem {
    private Book book;
    private Integer num;
}

@Data
public class Order {
    Date date;
    String address;
    List<OrderItem> books;
}
