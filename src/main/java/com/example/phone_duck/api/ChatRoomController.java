package com.example.phone_duck.api;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.service.ChatRoomService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("channels")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping
    private List<ChatRoom> showAllChatRoom(){
        return chatRoomService.readAll();
    }

    @PostMapping("create")
    private String createChatRoom(@RequestBody ChatRoom chatRoom) {
        chatRoomService.create(chatRoom);
        return "Chat Room was created";
    }

}
