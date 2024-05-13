package khds.ecommerce.chatting.stomp.test;

import java.util.List;
import khds.ecommerce.chatting.stomp.StompChatDTO;
import khds.ecommerce.chatting.stomp.StompChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StompChatRoomRestController {

//    @Autowired
//    private StompChatService chatService;

    private final SimpMessageSendingOperations template;

    // 채팅 리스트 화면
    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return
    @GetMapping("/chat/{id}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long id){
        ChatMessage test = new ChatMessage(1L, "test", "test");

        return ResponseEntity.ok().body(List.of(test));
    }

    @MessageMapping("/message")
    public ResponseEntity<String> receiveMessage(@RequestBody ChatMessage chat) {
        // 메시지 저장
        System.out.println("chat = " + chat);

        // 메시지를 해당 채팅방 구독자들에게 전송
        template.convertAndSend("/sub/chatroom/1", chat);
        return ResponseEntity.ok("메시지 전송 완료");
    }
}