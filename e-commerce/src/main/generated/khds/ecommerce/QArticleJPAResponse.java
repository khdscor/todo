package khds.ecommerce;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * khds.ecommerce.QArticleJPAResponse is a Querydsl Projection type for ArticleJPAResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QArticleJPAResponse extends ConstructorExpression<ArticleJPAResponse> {

    private static final long serialVersionUID = -1584619274L;

    public QArticleJPAResponse(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content) {
        super(ArticleJPAResponse.class, new Class<?>[]{long.class, String.class, String.class}, id, title, content);
    }

}

