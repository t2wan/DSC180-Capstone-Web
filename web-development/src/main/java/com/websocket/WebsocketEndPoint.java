package com.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
public class WebsocketEndPoint extends TextWebSocketHandler {
    private static WebSocketSession webSocketSession = null;
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session,message);
        System.out.println("前端发来消息："+message.getPayload());

//        for (int i = 0; i < 100; i++) {
//            TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server"+i);
//            session.sendMessage(returnMessage);
//        }
    }
    public boolean sendMessage(TextMessage message){
        boolean sendState = true;
        try {
            webSocketSession.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
            sendState = false;
        }
        return sendState;
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("建立连接成功");
        webSocketSession = session;
        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("消息发送失败");
        super.handleTransportError(session, exception);
    }
}
