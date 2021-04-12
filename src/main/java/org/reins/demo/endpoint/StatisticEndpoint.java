package org.reins.demo.endpoint;

import org.reins.demo.decoder.StatisticDecoder;
import org.reins.demo.encoder.VisitNMessageEncoder;
import org.reins.demo.service.StatisticService;
import org.reins.demo.service.impl.StatisticServiceImpl;
import org.reins.demo.socket_msg.GetVisitNMessage;
import org.reins.demo.socket_msg.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/statistic",
        decoders = {StatisticDecoder.class},
        encoders = {VisitNMessageEncoder.class}
)
@Component
public class StatisticEndpoint {
    static private StatisticService statisticService;

    @Autowired
    public void setStatisticService(StatisticServiceImpl statisticService) {
        StatisticEndpoint.statisticService = statisticService;
    }

    @OnOpen
    public void onOpen(Session session) {
        statisticService.addSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        statisticService.removeSession(session);
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Message msg, Session session) {
        if (msg instanceof GetVisitNMessage) {
            statisticService.getVisitNum(session);
        }
    }
}
