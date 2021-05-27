package org.reins.demo.encoder;

import org.reins.demo.model.Dialog;
import org.reins.demo.socket_msg.SystemMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class SystemMessageEncoder implements Encoder.Text<SystemMessage> {
    @Override
    public String encode(SystemMessage systemMessage) throws EncodeException {
        StringWriter sWriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(sWriter)) {
            jsonGen.writeStartObject()
                    .write("type", "system")
                    .write("content", systemMessage.getContent())
                    .write("chatName", systemMessage.getChatName())
                    .write("time", systemMessage.getTime().toString())
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
