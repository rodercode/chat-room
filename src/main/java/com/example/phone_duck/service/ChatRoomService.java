package com.example.phone_duck.service;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.repo.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepo chatRoomRepo;

    public List<ChatRoom> readAllChatRoom(){
        return chatRoomRepo.findAll();
    }

    public void createChatRoom(ChatRoom chatRoom){
        chatRoomRepo.save(chatRoom);
    }



}
