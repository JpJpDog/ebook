package org.reins.demo.service;

import org.reins.demo.entity.CartItemE;

import java.util.List;

public interface PayService {
    Integer payBooks(List<CartItemE> cartItemES, Integer userId);
}
