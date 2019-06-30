package demo.springdemo.controller;

import demo.springdemo.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class WebSocketController {
    @Autowired
    private WebSocketServer server;

    @RequestMapping("/websocket")
    public String init() {
        return "websocket.html";
    }

    @Scheduled(fixedDelay = 5000)
    public void fixDelay() {
        Date date = new Date();
        server.push2AllSession("time: " + date.toString());
    }

    public WebSocketServer getServer() {
        return server;
    }

    public void setServer(WebSocketServer server) {
        this.server = server;
    }
}
