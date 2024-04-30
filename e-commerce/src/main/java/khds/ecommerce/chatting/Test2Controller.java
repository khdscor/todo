package khds.ecommerce.chatting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

@RestController
public class Test2Controller {

    @Autowired
    private Test2Repository testRepository;

    @Autowired
    private Test3Repository test3Repository;

    CountDownLatch cdl = new CountDownLatch(2);

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

    @GetMapping("/saveR1")
    public String testSave1(@RequestParam("name") String name, @RequestParam("age") Long age) {
        ChattingContent content = new ChattingContent(name,age);
        Mono<ChattingContent> test = test3Repository.save(content);

        return "success";
    }

    @GetMapping("/saveR2")
    public Mono<ChattingContent> testSave2(@RequestParam("name") String name, @RequestParam("age") Long age) {
        ChattingContent content = new ChattingContent(name,age);

        return test3Repository.save(content);
    }

    @GetMapping("/saveR3")
    public String testSave3(@RequestParam("name") String name, @RequestParam("age") Long age) {
        System.out.println("111111111111111111111111");
        ChattingContent content = new ChattingContent(name,age);
        Mono<ChattingContent> test = test3Repository.save(content);
        test.subscribe(System.out::println);
        System.out.println("2222222222222222222");
        return "success";
    }

    @GetMapping("/saveR4")
    public String testSave4(@RequestParam("name") String name, @RequestParam("age") Long age) {
        System.out.println("111111111111111111111111");
        ChattingContent content = new ChattingContent(name,age);
        Mono<ChattingContent> test = test3Repository.save(content);

        test.block();
//        test.doOnTerminate();
        System.out.println("2222222222222222222");
        return "success";
    }

    @GetMapping("/saveR5")
    public Mono<ResponseEntity<ChattingContent>> testSave5(@RequestParam("name") String name, @RequestParam("age") Long age) {

        ChattingContent content = new ChattingContent(name,age);
        Mono<ChattingContent> test = test3Repository.save(content);
        return test.map(ResponseEntity::ok);
    }

    @GetMapping("/saveR6")
    public Mono<ResponseEntity<Void>> testSave6(@RequestParam("name") String name, @RequestParam("age") Long age) {

        ChattingContent content = new ChattingContent(name,age);

        return test3Repository.save(content).then(Mono.just(new ResponseEntity<Void>(HttpStatus.CREATED)));
    }

}
