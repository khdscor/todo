package khds.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/index")
    public String test(){
        return "dsasdindex";
    }

    @GetMapping("/products")
    public String test2(){
        return "testsfdfs";
    }
}
