package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "chattingRoom")
public class ChattingRoom extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private String roomName;
    private String roomId; //직렬화한 ID를 Redis에 저장

    @OneToMany(mappedBy = "chatting", cascade = CascadeType.ALL)
    private List<Chatting> chattings = new ArrayList<>();

    /*
    public static ChattingRoom create(String name) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.roomName = name;
        return chattingRoom;
    }
    */


    @Builder
    public ChattingRoom(String roomName, String roomId) {
        this.roomName = roomName;
        this.roomId = roomId;
    }


}
