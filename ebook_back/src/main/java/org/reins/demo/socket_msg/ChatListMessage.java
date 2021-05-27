package org.reins.demo.socket_msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.reins.demo.model.Dialog;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ChatListMessage extends Message {
    private List<Dialog> chatList;
    private String chatName;
}
