package khds.ecommerce.repository;

import java.util.List;
import khds.ecommerce.Article;
import khds.ecommerce.ArticleJPAResponse;

public interface ArticleCustomRepository {

    List<Article> findByTitleUnder10();

    List<ArticleJPAResponse> findDtoByTitleUnder10();
}
