package demo.springdemo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ServerEndpoint("/websocket")
public class WebSocketServer {
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static CopyOnWriteArrayList<WebSocketServer> queue = new CopyOnWriteArrayList<>();
    private static Integer count = 0;
    private Session session;
    boolean isOpen;

    @OnOpen
    public void open(Session session) {
        this.isOpen = true;
        this.session = session;
        queue.add(this);
        addCount();
        log.info("open... count: " + count);
        pushMessage("hello");
    }

    @OnClose
    public void close(Session session) {
        this.isOpen = false;
        queue.remove(this);
        deCount();
        log.info("close! count: " + count);
        pushMessage("goodbye");
    }

    @OnMessage
    public void reciveMessage(String message, Session session) {
        log.info("recive messaeg: " + message);
        for (WebSocketServer server : queue) {
            server.pushMessage(message);
        }
    }

    @OnError
    public void error(Session session, Throwable e) {
        log.error(e.getMessage());
    }

    public static synchronized void addCount() {
        count = count + 1;
    }

    public static synchronized void deCount() {
        count = count - 1;
    }

    /**
     * 推送消息
     *
     * @param mess
     */
    private void pushMessage(String mess) {
        try {
            if (isOpen) {
                this.session.getBasicRemote().sendText(mess);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给所有连接推送消息
     *
     * @param mess
     */
    public void push2AllSession(String mess) {
        for (WebSocketServer server : queue) {
            if (server.isOpen) {
                server.pushMessage(mess);
            }
        }
    }
}
