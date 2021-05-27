package org.reins.demo.socket_msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.reins.demo.model.UserStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserListMessage extends Message {
    private List<UserStatus> userList;
}
