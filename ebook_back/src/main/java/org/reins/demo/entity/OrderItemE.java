package org.reins.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orderItem")
@NoArgsConstructor
public class OrderItemE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer bookId;
    private Integer num;

    public OrderItemE(Integer orderId, Integer bookId, Integer num) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.num = num;
    }
}
