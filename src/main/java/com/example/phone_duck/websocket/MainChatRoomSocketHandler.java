package com.example.phone_duck.websocket;

import com.example.phone_duck.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Component
public class MainChatRoomSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> webSocketSessions = new ArrayList<>();
    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        broadcast(message.getPayload());
    }

    public void broadcast(String message) throws IOException {
        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(new TextMessage(message));
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        chatRoomService.readAllActiveChatRoom();
        webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
