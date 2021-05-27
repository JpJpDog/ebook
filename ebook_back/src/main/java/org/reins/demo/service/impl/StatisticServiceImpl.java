package org.reins.demo.service.impl;

import org.reins.demo.service.StatisticService;
import org.reins.demo.socket_msg.VisitNMessage;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class StatisticServiceImpl implements StatisticService {
    private final AtomicInteger visitNum = new AtomicInteger(0);
    private final Map<Session, Integer> sessions = new ConcurrentHashMap<>();
    private final Lock sendAllLock = new ReentrantLock();

    @Override
    public void addSession(Session session) {
        sessions.put(session, -1);
    }

    @Override
    public void removeSession(Session session) {
        sessions.remove(session);
    }

    @Override
    public void getVisitNum(Session session) {
        try {
            session.getBasicRemote().sendObject(new VisitNMessage(visitNum.get()));
        } catch (EncodeException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addVisitNum() {
        VisitNMessage vMsg = new VisitNMessage(visitNum.incrementAndGet());
        synchronized (this) {
            boolean successLock = sendAllLock.tryLock();
            if (!successLock) {
                return;
            }
        }
        boolean updated = false;
        do {
            try {
                do {
                    for (Session s : sessions.keySet()) {
                        s.getBasicRemote().sendObject(vMsg);
                        if (vMsg.getVisitN() != visitNum.get()) {
                            updated = true;
                            break;
                        }
                    }
                } while (updated);
            } catch (EncodeException | IOException e) {
                e.printStackTrace();
            } finally {
                synchronized (this) {
                    if (vMsg.getVisitN() != visitNum.get()) {
                        updated = true;
                    } else {
                        sendAllLock.unlock();
                    }
                }
            }
        } while (updated);
    }
}
