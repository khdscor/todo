package khds.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String tset(){
        return " 테스트입니다!";
    }
}
