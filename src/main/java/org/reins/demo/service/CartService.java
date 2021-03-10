package org.reins.demo.service;


import org.reins.demo.model.CartItem;

import java.util.List;

public interface CartService {
    void updateCartItem(Integer bookId, Integer num);

    void deleteCartItem(Integer bookId);

    Integer payCartItem(List<Integer> bookIds,Integer userId);

    List<CartItem> getCartItems();
}
