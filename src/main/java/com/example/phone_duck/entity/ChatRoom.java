package com.example.phone_duck.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "channels", uniqueConstraints = {
        @UniqueConstraint(name = "chatRoom_name_unique", columnNames = "name")
})
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String title;
}
