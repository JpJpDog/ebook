package org.reins.demo.service.impl;

import org.reins.demo.dao.UserDao;
import org.reins.demo.model.User;
import org.reins.demo.model.UserStatus;
import org.reins.demo.dao.impl.MultiChatDaoImpl;
import org.reins.demo.dao.impl.TwoChatDaoImpl;
import org.reins.demo.model.Dialog;
import org.reins.demo.service.ChatService;
import org.reins.demo.socket_msg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private UserDao userDao;

    final private MultiChatDaoImpl multiChatDao = new MultiChatDaoImpl();

    final private Map<String, TwoChatDaoImpl> twoChatDaoMap = new HashMap<>();

    @Override
    public void getJoinMsg(Session session, JoinMessage jMsg) {
        String userName = jMsg.getUserName();
        ChatMessage cMsg = new ChatMessage("system",
                "main", userName + " join the chat");
        multiChatDao.joinChat(new Dialog(cMsg));
        session.getUserProperties().put("userName", userName);
        session.getUserProperties().put("active", true);
        sendAll(session, new UserListMessage(getUserStatuses(session)));
        sendBack(session, new ChatListMessage(multiChatDao.getDialogList(), "main"));
        sendExcept(session, cMsg);
    }

    @Override
    public void getChatMsg(Session session, ChatMessage cMsg) {
        String chatName = cMsg.getChatName();
        if (chatName.equals("main")) {
            multiChatDao.addDialog(new Dialog(cMsg));
            sendAll(session, cMsg);
            return;
        }
        String user1 = cMsg.getUserName(), user2 = cMsg.getChatName();
        TwoChatDaoImpl twoChatDao = getTwoChatDao(user1, user2);
        twoChatDao.addDialog(new Dialog(cMsg));
        sendBack(session, cMsg);
        cMsg.setChatName(user1);
        sendOne(session, user2, cMsg);
    }

    @Override
    public void getQuitMsg(Session session, QuitMessage qMsg) {
        List<UserStatus> userStatuses = getUserStatuses(session);
        for (UserStatus userStatus : userStatuses) {
            if (userStatus.getUserName().equals(qMsg.getUserName())) {
                userStatus.setActive(false);
                break;
            }
        }
        ChatMessage cMsg = new ChatMessage("system",
                "main", qMsg.getUserName() + " quit the chat");
        multiChatDao.quitChat(new Dialog(cMsg));
        sendExcept(session, cMsg);
        sendAll(session, new UserListMessage(userStatuses));
    }

    @Override
    public void getFetchMsg(Session session, FetchMessage fMsg) {
        if (fMsg.getChatName().equals("main")) {
            sendBack(session, new ChatListMessage(multiChatDao.getDialogList(), "main"));
            return;
        }
        String user1 = (String) session.getUserProperties().get("userName"), user2 = fMsg.getChatName();
        TwoChatDaoImpl twoChatDao = getTwoChatDao(user1, user2);
        sendBack(session, new ChatListMessage(twoChatDao.getDialogList(), fMsg.getChatName()));
    }

    private TwoChatDaoImpl getTwoChatDao(String user1, String user2) {
        if (user1.compareTo(user2) < 0) {
            String tmp = user1;
            user1 = user2;
            user2 = tmp;
        }
        String chatName = user1 + "+" + user2;
        TwoChatDaoImpl twoChatDao;
        if (!twoChatDaoMap.containsKey(chatName)) {
            twoChatDao = new TwoChatDaoImpl(user1, user2);
            twoChatDaoMap.put(chatName, twoChatDao);
        } else {
            twoChatDao = twoChatDaoMap.get(chatName);
        }
        return twoChatDao;
    }


    private List<UserStatus> getUserStatuses(Session session) {
        List<User> users = userDao.findAll();
        List<UserStatus> userStatuses = new ArrayList<>();
        Set<String> activeSet = new HashSet<>();
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen() && s.getUserProperties().get("active") != null) {
                activeSet.add((String) s.getUserProperties().get("userName"));
            }
        }
        for (User user : users) {
            userStatuses.add(new UserStatus(user.getName(), activeSet.contains(user.getName())));
        }
        return userStatuses;
    }


    private synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && s.getUserProperties().get("active") != null) {
                    s.getBasicRemote().sendObject(msg);
                }
            }
        } catch (IOException | EncodeException ignored) {
        }
    }

    private synchronized void sendOne(Session session, String userName, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                String user1 = (String) s.getUserProperties().get("userName");
                if (user1 == null) {
                    continue;
                }
                if (s.isOpen() && user1.equals(userName)
                        && s.getUserProperties().get("active") != null){
                    s.getBasicRemote().sendObject(msg);
                    return;
                }
            }
        } catch (IOException | EncodeException ignored) {
        }
    }

    public synchronized void sendBack(Session session, Object msg) {
        try {
            session.getBasicRemote().sendObject(msg);
        } catch (EncodeException | IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sendExcept(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && s != session && s.getUserProperties().get("active") != null) {
                    s.getBasicRemote().sendObject(msg);
                    return;
                }
            }
        } catch (IOException | EncodeException ignored) {
        }
    }
}
