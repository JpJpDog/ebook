package org.reins.demo.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private Integer bookId;
    private String bookName;
    private Integer price;
    private Integer stock;
    private String description;
}
