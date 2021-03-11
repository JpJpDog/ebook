package org.reins.demo.controller;

import net.sf.json.JSONObject;
import org.reins.demo.model.CartItem;
import org.reins.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostMapping("/update")
    void updateCart(Integer bookId, Integer num) {
        CartService cartService = webApplicationContext.getBean(CartService.class);
        cartService.updateCartItem(bookId, num);
    }

    @PostMapping("/delete")
    void deleteCart(Integer bookId) {
        CartService cartService = webApplicationContext.getBean(CartService.class);
        cartService.deleteCartItem(bookId);
    }

    @PostMapping("/toOrder")
    Integer toOrderCart(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer userId = (Integer) jsonObject.get("userId");
        String address = (String) jsonObject.get("address");
        List<Integer> bookIds = (List<Integer>) jsonObject.get("bookIds");
        CartService cartService = webApplicationContext.getBean(CartService.class);
        return cartService.toOrder(bookIds, userId, address);
    }

    @GetMapping("/getList")
    List<CartItem> getCarts() {
        CartService cartService = webApplicationContext.getBean(CartService.class);
        return cartService.getCartItems();
    }
}
