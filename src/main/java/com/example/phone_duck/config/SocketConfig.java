package com.example.phone_duck.config;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.websocket.ChatRoomSocketHandler;
import com.example.phone_duck.websocket.MainChatRoomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatRoomSocketHandler chatRoomSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatRoomSocketHandler,"/sub/channels");
        registry.addHandler(new ChatRoomSocketHandler(), "/sub/chat");
    }
}
