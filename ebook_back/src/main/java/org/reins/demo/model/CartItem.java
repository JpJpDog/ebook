package org.reins.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CartItem {
    Book book;
    Date date;
    Integer num;
}
