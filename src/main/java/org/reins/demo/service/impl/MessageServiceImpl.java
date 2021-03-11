package org.reins.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.reins.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Integer sendMsg(String topic, Object msg) {
        ProducerRecord<String, String> pr;
        try {
            pr = new ProducerRecord<>(topic, objectMapper.writeValueAsString(msg));
        } catch (Exception e) {
            return -1;
        }
        System.out.println(msg.getClass().getName().getBytes(StandardCharsets.UTF_8));
        pr.headers().add("type", msg.getClass().getName().getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(pr);
        return 0;
    }
}
