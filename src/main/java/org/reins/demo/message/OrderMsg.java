package org.reins.demo.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderMsg {
    private Integer userId;
    private String address;
    private List<OrderItemMsg> items;
}
