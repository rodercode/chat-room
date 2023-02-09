package com.example.phone_duck.websocket;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.ArrayList;
import java.util.List;


public class MainChatRoomSocketHandler {

    private List<WebSocketSession> webSocketSessions = new ArrayList<>();

}
