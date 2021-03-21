package org.reins.demo.dao.impl;

import org.reins.demo.dao.ChatDao;
import org.reins.demo.model.Dialog;
import org.springframework.stereotype.Service;

import java.util.*;

public abstract class ChatDaoImpl implements ChatDao {
    final protected List<Dialog> dialogList = new ArrayList<>();

    @Override
    public Integer addDialog(Dialog dialog) {
        dialogList.add(dialog);
        return 1;
    }

    @Override
    public List<Dialog> getDialogList() {
        return dialogList;
    }

    @Override
    public Dialog getLastDialog() {
        if (dialogList.isEmpty()) {
            return null;
        }
        return dialogList.get(dialogList.size() - 1);
    }
}
