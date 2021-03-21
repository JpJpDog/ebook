package org.reins.demo.dao.impl;

import org.reins.demo.model.Dialog;

public class MultiChatDaoImpl extends ChatDaoImpl {

    public void joinChat(Dialog joinMsg) {
        dialogList.add(joinMsg);
    }

    public Integer quitChat(Dialog quitMsg) {
        dialogList.add(quitMsg);
        return 0;
    }
}
