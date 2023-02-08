package com.example.phone_duck.service;

import com.example.phone_duck.entity.ChatRoom;
import com.example.phone_duck.repo.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepo chatRoomRepo;

    

}
