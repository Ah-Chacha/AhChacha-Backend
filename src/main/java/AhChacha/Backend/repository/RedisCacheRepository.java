package AhChacha.Backend.repository;

import AhChacha.Backend.domain.ChattingRoom;
import AhChacha.Backend.redis.RedisSubscriber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RedisCacheRepository {
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;


    private final RedisTemplate<String, Object> redisTemplate;
    private static final String Room = "ChattingRoom";
    private HashOperations<String, Long, ChattingRoom> opsHashChattingRoom;
    //public interface HashOperations<H,HK,HV>  해쉬 이름, 키, 밸류
    private Map<String, ChannelTopic> topics;



    @PostConstruct
    private void init() {
        opsHashChattingRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }
    public List<ChattingRoom> findAllRooms() {
        return opsHashChattingRoom.values(Room);
    }

    public ChattingRoom createChattingRoom(String name) {
        ChattingRoom chattingRoom = ChattingRoom.create(name);
        opsHashChattingRoom.put(Room, chattingRoom.getId(), chattingRoom);
        return chattingRoom;
    }

    public void enterChattingRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if(topic == null)
            topic = new ChannelTopic(roomId);
        redisMessageListenerContainer.addMessageListener(redisSubscriber, topic);
        topics.put(roomId, topic);
    }

    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }

}
