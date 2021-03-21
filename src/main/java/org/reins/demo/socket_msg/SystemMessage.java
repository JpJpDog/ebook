package org.reins.demo.socket_msg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemMessage extends Message{
    private String content;
    private String chatName;
    final private Date time;

    public SystemMessage(String content, String chatName) {
        this.content = content;
        this.chatName = chatName;
        time = new Date();
    }
}
