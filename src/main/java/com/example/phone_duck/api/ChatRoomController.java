package com.example.phone_duck.api;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.service.ChatRoomService;
import com.example.phone_duck.websocket.MainChatRoomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("channels")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MainChatRoomSocketHandler chatRoomSocketHandler;

    @GetMapping
    private ResponseEntity<List<ChatRoom>> showAllChatRoom() throws IOException {
        if (chatRoomService.readAll().isEmpty()) {
            return ResponseEntity
                    .status(204)
                    .header("x-information", "there are no ChatRooms active")
                    .build();
        }
        for (ChatRoom chatRoom : chatRoomService.readAll()) {
            chatRoomSocketHandler.broadcast("Active Channel: " + chatRoom.getName());
        }
        return new ResponseEntity<>(chatRoomService.readAll(), HttpStatus.OK);
    }

    @PostMapping("create")
    private ResponseEntity<String> createChatRoom(@RequestBody ChatRoom chatRoom) throws IOException {
        try {
            chatRoomService.create(chatRoom);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(422)
                    .header("error-information", "Please enter a different name, this name already exist")
                    .build();
        }
        chatRoomSocketHandler.broadcast("Created: "+chatRoom.getName());
        return new ResponseEntity<>("Chat room has been created", HttpStatus.CREATED);
    }

    @DeleteMapping("{id}/delete")
    private ResponseEntity<String> deleteChatRoom(@PathVariable("id") Long id) throws IOException {
        try {
            chatRoomService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity
                    .status(404)
                    .header("x-information", "ChatRoom you were tried to delete does not exist")
                    .build();
        }
        chatRoomSocketHandler.broadcast("Deleted: Chat Room");
        return new ResponseEntity<>("Channel Deleted: Chat Room", HttpStatus.OK);
    }

}
