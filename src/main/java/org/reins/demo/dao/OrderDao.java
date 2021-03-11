package org.reins.demo.dao;

import org.reins.demo.entity.OrderE;
import org.reins.demo.entity.OrderItemE;
import org.reins.demo.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    List<OrderE> findOrderByUserId(Integer userId);

    List<OrderItemE> findOrderItemByOrderId(Integer bookId);
}
