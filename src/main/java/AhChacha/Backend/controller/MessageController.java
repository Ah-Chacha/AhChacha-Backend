package AhChacha.Backend.controller;

import AhChacha.Backend.domain.Message;
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

    @MessageMapping("/chat/message")
    public void message(Message message) {
        if (Message.MessageType.ENTER.equals(message.getType())) {
            redisCacheRepository.enterChattingRoom(message.getRoomId());
            //message.setMessage(message.getSender() + "님이 입장하셨습니다.");
            //setter처리
        }

        redisPublisher.publish(redisCacheRepository.getTopic(message.getRoomId()), message);
    }
}
