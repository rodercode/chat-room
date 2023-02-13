package com.example.phone_duck.service;
import com.example.phone_duck.Model.ChatRoom;
import com.example.phone_duck.repo.ChatRoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepo chatRoomRepo;
    public ChatRoom getChatRoom(Long id){
        return chatRoomRepo.getReferenceById(id);
    }
    public void saveChatRoom(ChatRoom chatRoom){
        chatRoomRepo.save(chatRoom);
    }
    public List<ChatRoom> readAllActiveChatRoom(){
        return chatRoomRepo.findAllByIsOnlineTrue();
    }
    public List<ChatRoom> readAll(){
        return chatRoomRepo.findAll();
    }
    public void create(ChatRoom chatRoom){
        chatRoomRepo.save(chatRoom);
    }
    public void delete(Long id){
        chatRoomRepo.deleteById(id);
    }
}
