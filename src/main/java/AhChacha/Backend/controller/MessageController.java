package AhChacha.Backend.controller;

import AhChacha.Backend.domain.Message;
import AhChacha.Backend.dto.chatting.MessageDto;
import AhChacha.Backend.redis.RedisPublisher;
import AhChacha.Backend.repository.ChattingRoomRepository;
import AhChacha.Backend.repository.RedisCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MessageController {

    private final RedisPublisher redisPublisher;
    private final RedisCacheRepository redisCacheRepository;

    @MessageMapping("/chatting/message")
    public void message(MessageDto messageDto) {
        if (Message.MessageType.ENTER.equals(messageDto.getType())) {
            redisCacheRepository.enterChattingRoom(messageDto.getRoomId());
            messageDto.setMessage(messageDto.getSender() + "님이 입장하셨습니다.");
        }

        redisPublisher.publish(redisCacheRepository.getTopic(messageDto.getRoomId()), messageDto);
    }
}
