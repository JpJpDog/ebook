package org.reins.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
public class CartItemE {
    private Integer bookId;
    private Integer num;
    private Date date;
}
