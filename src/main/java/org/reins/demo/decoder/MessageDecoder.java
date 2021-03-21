package org.reins.demo.decoder;

import javax.json.Json;

import org.reins.demo.socket_msg.*;

import javax.json.stream.JsonParser;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessageDecoder implements Decoder.Text<Message> {
    private Map<String, String> messageMap;

    @Override
    public Message decode(String s) {
        Message msg = null;
        if (willDecode(s)) {
            switch (messageMap.get("type")) {
                case "hello":
                    msg = new HelloMessage();
                    break;
                case "join":
                    msg = new JoinMessage(messageMap.get("userName"));
                    break;
                case "chat":
                    msg = new ChatMessage(messageMap.get("userName"), messageMap.get("chatName"), messageMap.get("content"));
                    break;
                case "quit":
                    msg = new QuitMessage(messageMap.get("userName"));
                    break;
                case "fetch":
                    msg = new FetchMessage(messageMap.get("chatName"));
                    break;
            }
        }
        return msg;
    }

    @Override
    public boolean willDecode(String s) {
        boolean canDecode = false;
        messageMap = new HashMap<>();
        JsonParser parser = Json.createParser(new StringReader(s));
        while (parser.hasNext()) {
            if (parser.next() == JsonParser.Event.KEY_NAME) {
                String key = parser.getString();
                parser.next();
                String value = parser.getString();
                messageMap.put(key, value);
            }
        }
        Set<String> keys = messageMap.keySet();
        if (keys.contains("type")) {
            switch (messageMap.get("type")) {
                case "hello":
                    canDecode = true;
                    break;
                case "join":
                case "quit":
                    if (keys.contains("userName")) {
                        canDecode = true;
                    }
                    break;
                case "chat":
                    if (keys.contains("userName") && keys.contains("chatName") && keys.contains("content")) {
                        canDecode = true;
                    }
                    break;
                case "fetch":
                    if (keys.contains("chatName")) {
                        canDecode = true;
                    }
            }
        }
        return canDecode;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }
}
