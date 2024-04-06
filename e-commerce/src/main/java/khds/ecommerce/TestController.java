package khds.ecommerce;

import java.util.Date;
import java.util.List;
import khds.ecommerce.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/create")
    public String test(@RequestParam("title") String title, @RequestParam("content") String content){
        Article article = new Article(title, content, new Date());
        articleRepository.save(article);
        return "success";
    }

    @GetMapping("/products")
    public Article test2(){
        List<Article> article = articleRepository.findAll();
        return article.get(0);
    }
}
