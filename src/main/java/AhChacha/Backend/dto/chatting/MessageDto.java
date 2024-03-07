package AhChacha.Backend.dto.chatting;

import AhChacha.Backend.domain.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private Message.MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
