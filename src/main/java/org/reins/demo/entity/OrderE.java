package org.reins.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order")
@NoArgsConstructor
public class OrderE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String address;
    private Date date;

    public OrderE(Integer userId, String address, Date date) {
        this.userId = userId;
        this.address = address;
        this.date = date;
    }
}
