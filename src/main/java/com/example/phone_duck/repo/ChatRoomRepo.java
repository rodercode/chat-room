package com.example.phone_duck.repo;

import com.example.phone_duck.entity.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepo extends JpaRepository<ChatRoom,Long> {
}
