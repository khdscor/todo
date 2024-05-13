package khds.ecommerce.chatting.stomp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RestController
public class StompChatRoomRestController {

    @Autowired
    private StompChatService chatService;

    // 채팅 리스트 화면
    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return
    @GetMapping("/chat/{id}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable Long id){
        ChatMessage test = new ChatMessage(1L, "test", "test");

        return ResponseEntity.ok().body(List.of(test));
    }
}