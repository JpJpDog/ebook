package org.reins.demo.service;


import org.reins.demo.model.CartItem;

import java.util.List;

public interface CartService {
    void updateCartItem(Integer bookId, Integer num);

    void deleteCartItem(Integer bookId);

    Integer toOrder(List<Integer> bookIds, Integer userId, String address);

    List<CartItem> getCartItems();
}
