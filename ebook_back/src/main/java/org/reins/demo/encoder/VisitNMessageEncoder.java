package org.reins.demo.encoder;

import org.reins.demo.socket_msg.VisitNMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class VisitNMessageEncoder implements Encoder.Text<VisitNMessage> {
    @Override
    public String encode(VisitNMessage visitNMessage) throws EncodeException {
        StringWriter stringWriter = new StringWriter();
        try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {
            jsonGenerator.writeStartObject()
                    .write("type", "visitN")
                    .write("visitN", visitNMessage.getVisitN())
                    .writeEnd();
        }
        return stringWriter.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
