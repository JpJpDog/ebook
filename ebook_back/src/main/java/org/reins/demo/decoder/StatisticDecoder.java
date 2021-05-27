package org.reins.demo.decoder;

import org.reins.demo.socket_msg.GetVisitNMessage;
import org.reins.demo.socket_msg.Message;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StatisticDecoder implements Decoder.Text<Message> {
    private Map<String, String> messageMap;

    @Override
    public Message decode(String s) throws DecodeException {
        Message msg = null;
        if (willDecode(s)) {
            switch (messageMap.get("type")) {
                case "getVisitN":
                    msg = new GetVisitNMessage();
                    break;
            }
        }
        return msg;
    }

    @Override
    public boolean willDecode(String s) {
        boolean canDecoder = false;
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
                case "getVisitN":
                    canDecoder = true;
                    break;
            }
        }
        return canDecoder;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
