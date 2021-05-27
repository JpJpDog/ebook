package org.reins.demo.socket_msg;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisitNMessage extends Message{
    private Integer visitN;
}
