package org.reins.demo.endpoint;

import org.reins.demo.service.impl.ChatServiceImpl;
import org.reins.demo.decoder.ChatDecoder;
import org.reins.demo.encoder.*;
import org.reins.demo.socket_msg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(
        value = "/chatroom",
        decoders = {ChatDecoder.class},
        encoders = {HelloMessageEncoder.class, UserListMessageEncoder.class, SystemMessageEncoder.class, ChatMessageEncoder.class, ChatListMessageEncoder.class}
)
@Component
public class ChatEndpoint {
    static private ChatServiceImpl chatService;

    @Autowired
    public void setChatService(ChatServiceImpl chatService) {
        ChatEndpoint.chatService = chatService;
    }

    @OnOpen
    public void onOpen(Session session) {
    }

    @OnClose
    public void onClose(Session session) {
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(final Session session, Message msg) {
        if (msg instanceof HelloMessage) {
            chatService.sendBack(session, new HelloMessage());
        } else if (msg instanceof JoinMessage) {
            JoinMessage jMsg = (JoinMessage) msg;
            chatService.getJoinMsg(session, jMsg);
        } else if (msg instanceof ChatMessage) {
            ChatMessage cMsg = (ChatMessage) msg;
            chatService.getChatMsg(session, cMsg);
        } else if (msg instanceof QuitMessage) {
            QuitMessage qMsg = (QuitMessage) msg;
            chatService.getQuitMsg(session, qMsg);
        } else if (msg instanceof FetchMessage) {
            FetchMessage fMsg = (FetchMessage) msg;
            chatService.getFetchMsg(session, fMsg);
        }
    }
}
