package org.reins.demo.service.impl;

import org.reins.demo.dao.BookDao;
import org.reins.demo.entity.CartItemE;
import org.reins.demo.message.OrderItemMsg;
import org.reins.demo.message.OrderMsg;
import org.reins.demo.model.CartItem;
import org.reins.demo.service.CartService;
import org.reins.demo.service.MessageService;
import org.reins.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope("session")
public class CartServiceImpl implements CartService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private MessageService messageService;

    final private Map<Integer, CartItemE> cartItemEMap = new HashMap<>();
    final private LinkedList<CartItemE> cartItemES = new LinkedList<>();

    @Override
    public void updateCartItem(Integer bookId, Integer num) {
        CartItemE cartItem = cartItemEMap.get(bookId);
        if (cartItem == null) {
            CartItemE newItem = new CartItemE(bookId, num, new Date());
            cartItemEMap.put(bookId, newItem);
            cartItemES.offer(newItem);
        } else {
            cartItem.setDate(new Date());
            cartItem.setNum(num);
            cartItemES.remove(cartItem);
            cartItemES.offer(cartItem);
        }
    }

    @Override
    public void deleteCartItem(Integer bookId) {
        CartItemE cartItemE = cartItemEMap.remove(bookId);
        cartItemES.remove(cartItemE);
    }

    @Override
    public Integer toOrder(List<Integer> bookIds, Integer userId, String address) {
        List<OrderItemMsg> orderItemMsgs = new ArrayList<>(bookIds.size());
        for (Integer bookId : bookIds) {
            CartItemE cartItemE = cartItemEMap.remove(bookId);
            cartItemES.remove(cartItemE);
            orderItemMsgs.add(new OrderItemMsg(cartItemE.getBookId(), cartItemE.getNum()));
        }
        return messageService.sendMsg("book_order", new OrderMsg(userId, address, orderItemMsgs));
    }

    @Override
    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>(cartItemES.size());
        for (CartItemE cartItemE : cartItemES) {
            cartItems.add(new CartItem(bookDao.findById(cartItemE.getBookId()), cartItemE.getDate(), cartItemE.getNum()));
        }
        return cartItems;
    }
}
