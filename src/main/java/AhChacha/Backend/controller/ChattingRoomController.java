package AhChacha.Backend.controller;

import AhChacha.Backend.domain.ChattingRoom;
import AhChacha.Backend.repository.RedisCacheRepository;
import AhChacha.Backend.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/chatting")
public class ChattingRoomController {
    private final ChattingRoomService chattingRoomService;
    private final RedisCacheRepository redisCacheRepository;

    @GetMapping("/rooms")
    public List<ChattingRoom> chattingRooms() {
        return redisCacheRepository.findAllRooms();
    }
}
