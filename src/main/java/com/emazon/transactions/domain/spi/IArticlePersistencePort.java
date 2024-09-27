package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.Article;
import com.emazon.transactions.domain.model.ArticleQuantity;

public interface IArticlePersistencePort {
    void updateArticleQuantity(ArticleQuantity articleQuantity);
    Article getArticleById(Long articleId);
}
