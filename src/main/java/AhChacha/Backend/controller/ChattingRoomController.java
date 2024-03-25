package AhChacha.Backend.controller;

import AhChacha.Backend.domain.ChattingRoom;
import AhChacha.Backend.dto.chatting.ChattingRoomDto;
import AhChacha.Backend.repository.RedisCacheRepository;
import AhChacha.Backend.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chatting")
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;
    private final RedisCacheRepository redisCacheRepository;

    @GetMapping("/rooms")
    public List<ChattingRoomDto> chattingRooms() {
        return redisCacheRepository.findAllRooms();
    }


    @PostMapping("/room")
    public ChattingRoomDto createRoom(@RequestBody String name) {
        return redisCacheRepository.createChattingRoom(name);
    }
}
