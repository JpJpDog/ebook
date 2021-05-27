package org.reins.demo.socket_msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class QuitMessage extends Message {
    private String userName;
}
