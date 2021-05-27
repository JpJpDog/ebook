package org.reins.demo.encoder;

import org.reins.demo.socket_msg.ChatMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {
    @Override
    public String encode(ChatMessage chatMessage) throws EncodeException {
        StringWriter sWriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(sWriter)) {
            jsonGen.writeStartObject()
                    .write("type", "chat")
                    .write("userName", chatMessage.getUserName())
                    .write("content", chatMessage.getContent())
                    .write("time", chatMessage.getTime().toString())
                    .write("chatName", chatMessage.getChatName())
                    .writeEnd();
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
