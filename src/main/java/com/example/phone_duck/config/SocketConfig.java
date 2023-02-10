package com.example.phone_duck.config;

import com.example.phone_duck.websocket.ChatRoomSocketHandler;
import com.example.phone_duck.websocket.MainChatRoomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Autowired
    private MainChatRoomSocketHandler mainChatRoomSocketHandler;
    @Autowired
    private ChatRoomSocketHandler chatRoomSocketHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(mainChatRoomSocketHandler,"/sub/channels");
        registry.addHandler(chatRoomSocketHandler,"/sub/chat/");
    }
}
