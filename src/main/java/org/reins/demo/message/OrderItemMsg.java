package org.reins.demo.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public  class OrderItemMsg {
    private Integer bookId;
    private Integer num;
}
