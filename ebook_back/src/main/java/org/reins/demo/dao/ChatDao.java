package org.reins.demo.dao;

import org.reins.demo.model.Dialog;

import java.util.List;

public interface ChatDao {
    Integer addDialog(Dialog dialog);

    List<Dialog> getDialogList();

    Dialog getLastDialog();
}
