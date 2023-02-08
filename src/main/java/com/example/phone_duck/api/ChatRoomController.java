package com.example.phone_duck.api;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.service.ChatRoomService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("channels")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping
    private ResponseEntity<List<ChatRoom>> showAllChatRoom(){
        return new ResponseEntity<>(chatRoomService.readAll(), HttpStatus.OK);
    }

    @PostMapping("create")
    private ResponseEntity<String> createChatRoom(@RequestBody ChatRoom chatRoom) {
        chatRoomService.create(chatRoom);
        return new ResponseEntity<>("Chat Room was created",HttpStatus.CREATED);
    }

    @DeleteMapping("{id}/delete")
    private ResponseEntity<String> deleteChatRoom(@PathVariable("id") Long id){
        chatRoomService.delete(id);
        return new ResponseEntity<>("Chat Room was deleted",HttpStatus.OK) ;
    }

}
