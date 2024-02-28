package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chattingRoom")
public class ChattingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String roomName;

    @OneToMany(mappedBy = "chatting", cascade = CascadeType.ALL)
    private List<Chatting> chattings = new ArrayList<>();


}
