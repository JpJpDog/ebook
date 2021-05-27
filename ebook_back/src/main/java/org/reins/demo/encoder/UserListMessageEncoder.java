package org.reins.demo.encoder;

import org.reins.demo.model.UserStatus;
import org.reins.demo.socket_msg.UserListMessage;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

public class UserListMessageEncoder implements Encoder.Text<UserListMessage> {
    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(UserListMessage userListMessage) {
        StringWriter sWriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(sWriter)) {
            jsonGen.writeStartObject().write("type", "userList").writeStartArray("userList");
            for (UserStatus user : userListMessage.getUserList()) {
                jsonGen.writeStartObject()
                        .write("userName", user.getUserName())
                        .write("active", user.getActive())
                        .writeEnd();
            }
            jsonGen.writeEnd().writeEnd();

        }
        return sWriter.toString();
    }
}
