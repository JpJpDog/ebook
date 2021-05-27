package org.reins.demo.repository;

import org.reins.demo.entity.OrderE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderE, Integer> {
    List<OrderE> findAllByUserId(Integer userId);
}
