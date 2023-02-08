package com.example.phone_duck.api;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("channels")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @PostMapping("create")
    private String createChatRoom(@RequestBody ChatRoom chatRoom) {
        chatRoomService.create(chatRoom);
        return "Chat Room was created";
    }

}
