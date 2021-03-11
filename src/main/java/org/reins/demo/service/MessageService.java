package org.reins.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageService {
    Integer sendMsg(String topic, Object msg);
}
