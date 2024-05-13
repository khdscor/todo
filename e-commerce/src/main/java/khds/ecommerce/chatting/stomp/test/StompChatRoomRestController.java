package khds.ecommerce.chatting.stomp.test;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompChatRoomRestController {

    private final SimpMessageSendingOperations template;

    // 채팅 리스트 화면
    @GetMapping("/chat/{id}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long id){
        ChatMessage test = new ChatMessage(1L, "test", "test");

        return ResponseEntity.ok().body(List.of(test));
    }

    //메세지 송신 및 수신
    @MessageMapping("/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody ChatMessage chat) {
        // 메시지를 해당 채팅방 구독자들에게 전송
        template.convertAndSend("/sub/chatroom/1", chat);
        return ResponseEntity.ok().build();
    }
}