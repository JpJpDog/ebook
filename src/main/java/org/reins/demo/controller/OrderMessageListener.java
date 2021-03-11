package org.reins.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.reins.demo.message.OrderMsg;
import org.reins.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class OrderMessageListener {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;

    private static <T> Class<T> getType(String type) {
        try {
            return (Class<T>) Class.forName(type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "book_order", groupId = "group1")
    public Integer onOrderMessage(@Payload String message, @Header("type") String type) {
        OrderMsg msg;
        try {
            msg = objectMapper.readValue(message, getType(type));
        } catch (Exception e) {
            return -1;
        }
        return orderService.addOrder(msg);
    }
}
