//package khds.ecommerce.chatting.stomp;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Slf4j
//@Controller
//public class StompChatRoomController {
//
//    @Autowired
//    private StompChatService chatService;
//
//    // 채팅 리스트 화면
//    // / 로 요청이 들어오면 전체 채팅룸 리스트를 담아서 return
//    @GetMapping("/")
//    public String goChatRoom(Model model){
//
//        model.addAttribute("list", chatService.findAllRoom());
////        model.addAttribute("user", "hey");
//        log.info("SHOW ALL ChatList {}", chatService.findAllRoom());
//        return "roomlist";
//    }
//
//    // 채팅방 생성
//    // 채팅방 생성 후 다시 / 로 return
//    @PostMapping("/chat/createroom")
//    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
//        StompChatRoom room = chatService.createChatRoom(name);
//        log.info("CREATE Chat Room {}", room);
//        rttr.addFlashAttribute("roomName", room);
//        return "redirect:/";
//    }
//
//    // 채팅방 입장 화면
//    // 파라미터로 넘어오는 roomId 를 확인후 해당 roomId 를 기준으로
//    // 채팅방을 찾아서 클라이언트를 chatroom 으로 보낸다.
//    @GetMapping("/chat/room")
//    public String roomDetail(Model model, String roomId){
//
//        log.info("roomId {}", roomId);
//        model.addAttribute("room", chatService.findRoomById(roomId));
//        return "chatroom";
//    }
//}