package khds.ecommerce;

import java.util.Date;
import java.util.List;
import khds.ecommerce.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/create")
    public String test1(@RequestParam("title") String title, @RequestParam("content") String content){
        Article article = new Article(title, content, new Date());
        articleRepository.save(article);
        return "success";
    }

    @GetMapping("/find")
    public List<Article> test2(){
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    @GetMapping("/find2")
    public List<Article> test3(){
        List<Article> articles = articleRepository.findByTitleUnder3();
        return articles;
    }

    @GetMapping("/find3")
    public List<ArticleJPAResponse> test4(){
        List<ArticleJPAResponse> articles = articleRepository.findDtoByTitleUnder3();
        return articles;
    }
}