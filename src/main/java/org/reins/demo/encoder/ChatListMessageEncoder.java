package org.reins.demo.encoder;

import org.reins.demo.model.Dialog;
import org.reins.demo.socket_msg.ChatListMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class ChatListMessageEncoder implements Encoder.Text<ChatListMessage> {
    @Override
    public String encode(ChatListMessage chatListMessage) {
        StringWriter sWriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(sWriter)) {
            jsonGen.writeStartObject().write("type", "chatList").writeStartArray("chatList");
            for (Dialog dialog : chatListMessage.getChatList()) {
                jsonGen.writeStartObject()
                        .write("content", dialog.getContent())
                        .write("userName", dialog.getUserName())
                        .write("time", dialog.getTime().toString())
                        .writeEnd();
            }
            jsonGen.writeEnd().writeEnd();

        }
        return sWriter.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
