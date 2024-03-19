package khds.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/index")
    public String test(){
        return "/index";
    }

    @GetMapping("/products")
    public String test2(){
        return "/products/products";
    }
}
