package com.example.phone_duck.config;

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
    private MainChatRoomSocketHandler chatRoomSocketHandler;
    private String name = "chatroom";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatRoomSocketHandler,"/sub/channels");
        registry.addHandler(new MainChatRoomSocketHandler(), "/sub/chat/"+ name);
    }
}
