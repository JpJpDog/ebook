package org.reins.demo.dao.impl;

import lombok.Getter;

@Getter
public class TwoChatDaoImpl extends ChatDaoImpl {
    final private String user1;
    final private String user2;

    public TwoChatDaoImpl(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
