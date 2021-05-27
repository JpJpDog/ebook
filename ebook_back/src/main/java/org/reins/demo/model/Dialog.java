package org.reins.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.reins.demo.socket_msg.ChatMessage;
import org.reins.demo.socket_msg.Message;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Dialog extends Message {
    private String userName;
    private String content;
    private Date time;
    public Dialog(ChatMessage chatMessage){
        this.userName=chatMessage.getUserName();
        this.content=chatMessage.getContent();
        this.time=chatMessage.getTime();
    }
}
