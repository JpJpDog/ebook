package org.reins.demo.service;

import org.reins.demo.endpoint.ChatEndpoint;
import org.reins.demo.socket_msg.ChatMessage;
import org.reins.demo.socket_msg.FetchMessage;
import org.reins.demo.socket_msg.JoinMessage;
import org.reins.demo.socket_msg.QuitMessage;

import javax.websocket.Session;

public interface ChatService {
    void getJoinMsg(Session session, JoinMessage jMsg);

    void getChatMsg(Session session, ChatMessage cMsg);

    void getQuitMsg(Session session, QuitMessage qMsg);

    void getFetchMsg(Session session, FetchMessage fMsg);
}
