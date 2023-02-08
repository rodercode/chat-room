package com.example.phone_duck.repo;

import com.example.phone_duck.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {
}
