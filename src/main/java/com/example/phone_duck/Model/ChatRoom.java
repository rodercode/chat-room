package com.example.phone_duck.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "channels", uniqueConstraints = {
        @UniqueConstraint(name = "chatRoom_name_unique", columnNames = "name")
})
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    private Boolean isOnline = false;
}
