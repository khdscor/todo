package khds.ecommerce.chatting.stomp;

import java.util.HashMap;
import java.util.UUID;
import lombok.Data;

@Data
public class StompChatRoom {
    private String roomId; // 채팅방 아이디
    private String roomName; // 채팅방 이름
    private long userCount; // 채팅방 인원수

    private HashMap<String, String> userlist = new HashMap<String, String>();

    public StompChatRoom create(String roomName){
        StompChatRoom chatRoom = new StompChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.roomName = roomName;

        return chatRoom;
    }
}