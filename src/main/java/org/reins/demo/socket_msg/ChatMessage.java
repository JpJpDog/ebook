package org.reins.demo.socket_msg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatMessage extends Message {
    private String userName;
    private String chatName;
    private String content;
    final private Date time;

    public ChatMessage(String userName, String chatName, String content) {
        this.userName = userName;
        this.chatName = chatName;
        this.content = content;
        this.time = new Date();
    }
}
