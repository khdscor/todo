package khds.ecommerce.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test2Controller {

    @Autowired
    private Test2Repository testRepository;

    @GetMapping("/save")
    public ChattingContent test(@RequestParam("name") String name, @RequestParam("age") Long age) {
        ChattingContent content = new ChattingContent(name,age);

        return testRepository.save(content);
    }

    @GetMapping("/findfind")
    public ChattingContent test(@RequestParam("name") String name) {
        ChattingContent content = testRepository.findChattingContentByName(name);

        return content;
    }
}
