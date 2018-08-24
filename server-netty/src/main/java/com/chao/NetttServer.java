package com.chao;

import com.chao.server.chatclient.ChatServer;
import com.chao.server.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetttServer {
    private final static Logger logger = LoggerFactory.getLogger(NetttServer.class);

    public static void main(String[] args) {
        start();
    }

    public static void start() {

        new Thread() {
            @Override
            public void run() {
                try {
                    new ChatServer(9902).run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    new WebSocketServer(9901).run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
