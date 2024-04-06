package khds.ecommerce.repository;

import static khds.ecommerce.QArticle.article;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import khds.ecommerce.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public ArticleCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Article> findByTitleUnder10() {
        BooleanExpression expression = article.title.length().loe(10);
        return jpaQueryFactory.selectFrom(article).where(expression).fetch();
    }
}
