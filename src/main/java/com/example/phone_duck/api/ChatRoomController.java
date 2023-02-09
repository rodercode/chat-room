package com.example.phone_duck.api;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.service.ChatRoomService;
import com.example.phone_duck.websocket.ChatRoomSocketHandler;
import lombok.Getter;
import org.aspectj.weaver.ast.Not;
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
    private ChatRoomSocketHandler chatRoomSocketHandler;

    @GetMapping
    private ResponseEntity<List<ChatRoom>> showAllChatRoom(){
        if (chatRoomService.readAll().isEmpty()){
            return ResponseEntity
                    .status(204)
                    .header("x-information", "there are no ChatRooms active")
                    .build();
        }
        return new ResponseEntity<>(chatRoomService.readAll(), HttpStatus.OK);
    }

    @PostMapping("create")
    private ResponseEntity<String> createChatRoom(@RequestBody ChatRoom chatRoom) throws IOException {
        try {
            chatRoomService.create(chatRoom);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity
                    .status(422)
                    .header("error-information","Please enter a different name, this name already exist")
                    .build();
        }
        chatRoomSocketHandler.broadcast(chatRoom.getName()+ "Has been created");
        return new ResponseEntity<>("Chat Room was created",HttpStatus.CREATED);
    }

    @DeleteMapping("{id}/delete")
    private ResponseEntity<String> deleteChatRoom(@PathVariable("id") Long id) throws IOException {
        String name = chatRoomService.read(id).getName();
        try{
            chatRoomService.delete(id);
        }catch (EmptyResultDataAccessException e){
           return ResponseEntity
                   .status(404)
                   .header("x-information","ChatRoom you were tried to delete does not exist")
                   .build();
        }
        chatRoomSocketHandler.broadcast(name +" Has been deleted");
        return new ResponseEntity<>("Chat Room was deleted",HttpStatus.OK) ;
    }

}
