package org.reins.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "BookItem")
public class BookItemE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer bookId;

    private Integer num;
}
