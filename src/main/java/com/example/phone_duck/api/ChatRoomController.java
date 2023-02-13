package com.example.phone_duck.api;

import com.example.phone_duck.Model.ChatRoom;
import com.example.phone_duck.exception.ListEmptyException;
import com.example.phone_duck.exception.ResourceNotFoundException;
import com.example.phone_duck.exception.UniqueValidationException;
import com.example.phone_duck.service.ChatRoomService;
import com.example.phone_duck.websocket.ChatRoomSocketHandler;
import com.example.phone_duck.websocket.MainChatRoomSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("channels")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private MainChatRoomSocketHandler mainChatRoomSocketHandler;

    @Autowired
    private ChatRoomSocketHandler chatRoomSocketHandler;

    @GetMapping
    private ResponseEntity<List<ChatRoom>> showAllChatRoom() throws IOException {
        if (chatRoomService.readAll().isEmpty())
            throw new ListEmptyException("List is empty");
        else {
            for (ChatRoom chatRoom : chatRoomService.readAll()) {
                mainChatRoomSocketHandler.broadcast("Active Channel: " + chatRoom.getName());
            }
            return new ResponseEntity<>(chatRoomService.readAll(), HttpStatus.OK);
        }
    }
    @PostMapping("create")
    private ResponseEntity<String> createChatRoom(@RequestBody ChatRoom chatRoom) throws IOException {
        if (chatRoomService.getChatRoom(chatRoom.getName()) != null)
            throw new UniqueValidationException("There already exist a Chat Room with this name");
        else {
            chatRoomService.create(chatRoom);
            mainChatRoomSocketHandler.broadcast("Created: ");
            return new ResponseEntity<>(chatRoom.getName() + " has been created", HttpStatus.CREATED);
        }
    }

    @PatchMapping("{status}/{id}/update")
    private ResponseEntity<String> activateChatRoom(@PathVariable String status, @PathVariable Long id) {
        if (chatRoomService.getChatRoom(id).isEmpty())
            throw new ResourceNotFoundException("Could not update chat room because it doesn't exist");
        else {
            Optional<ChatRoom> chatRoom = chatRoomService.getChatRoom(id);
            switch (status) {
                case "online" -> chatRoom.get().setIsOnline(true);
                case "offline" -> chatRoom.get().setIsOnline(false);
                default -> throw new IllegalStateException(status + "was not defined");
            }
            chatRoomService.saveChatRoom(chatRoom.get());
            return new ResponseEntity<>("Chat Room is " + status, HttpStatus.OK);
        }
    }
    @DeleteMapping("{id}/delete")
    private ResponseEntity<String> deleteChatRoom(@PathVariable("id") Long id) throws IOException {
        if (chatRoomService.getChatRoom(id).isEmpty())
            throw new ResourceNotFoundException("Chat Room you were trying to delete does not exist");
        else {
            chatRoomService.delete(id);
            mainChatRoomSocketHandler.broadcast("Deleted: Chat Room");
            return new ResponseEntity<>("Channel Deleted: Chat Room", HttpStatus.OK);
        }
    }
}


