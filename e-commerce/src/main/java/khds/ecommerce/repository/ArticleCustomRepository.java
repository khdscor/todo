package khds.ecommerce.repository;

import java.util.List;
import khds.ecommerce.Article;

public interface ArticleCustomRepository {

    List<Article> findByTitleUnder10();
}
