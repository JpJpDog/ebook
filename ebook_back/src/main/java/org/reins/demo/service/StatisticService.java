package org.reins.demo.service;

import javax.websocket.Session;

public interface StatisticService {
    void addSession(Session session);

    void removeSession(Session session);

    void getVisitNum(Session session);

    void addVisitNum();
}
