package khds.ecommerce.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {

    @Autowired
    private Test2Repository testRepository;

    @GetMapping("/test")
    public ChattingContent test() {
        ChattingContent content = new ChattingContent("writer",14L);

//        ChattingContent content = testRepository.findChattingContentByName("test");
        return testRepository.save(content);
    }
}
