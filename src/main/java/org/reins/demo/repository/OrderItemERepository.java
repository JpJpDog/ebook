package org.reins.demo.repository;

import org.reins.demo.entity.OrderItemE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemERepository extends JpaRepository<OrderItemE, Integer> {
    List<OrderItemE> findAllByOrderId(Integer orderId);
}
