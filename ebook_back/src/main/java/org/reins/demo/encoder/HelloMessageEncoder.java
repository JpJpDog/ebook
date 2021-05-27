package org.reins.demo.encoder;

import org.reins.demo.socket_msg.HelloMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class HelloMessageEncoder implements Encoder.Text<HelloMessage> {

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public String encode(HelloMessage helloMessage) throws EncodeException {
        StringWriter sWriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(sWriter)) {
            jsonGen.writeStartObject().write("type", "hello").writeEnd();
        }
        return sWriter.toString();
    }
}
