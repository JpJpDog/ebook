package org.reins.demo.controller;

import com.mongodb.util.JSON;
import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
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

    @PostMapping("/pay")
    Integer payCart(@RequestBody String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        Integer userId = (Integer) jsonObject.get("userId");
        List<Integer> bookIds = (List<Integer>) jsonObject.get("bookIds");
        CartService cartService = webApplicationContext.getBean(CartService.class);
        return cartService.payCartItem(bookIds, userId);
    }

    @GetMapping("/getList")
    List<CartItem> getCarts() {
        CartService cartService = webApplicationContext.getBean(CartService.class);
        return cartService.getCartItems();
    }
}
