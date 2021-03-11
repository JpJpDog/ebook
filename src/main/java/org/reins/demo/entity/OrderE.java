package org.reins.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.reins.demo.model.Order;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "bookOrder")
@NoArgsConstructor
public class OrderE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String address;
    private Date date;
    private Boolean hasPay;

    public OrderE(Order order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.address = order.getAddress();
        this.date = order.getDate();
        this.hasPay = order.getHasPay();
    }

    public OrderE(Integer userId, String address, Date date) {
        this.userId = userId;
        this.address = address;
        this.date = date;
        this.hasPay = false;
    }
}
