package com.emazon.transactions.infrastructure.output.feign.adapter;

import com.emazon.transactions.domain.model.Article;
import com.emazon.transactions.domain.model.ArticleQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.infrastructure.output.feign.client.ArticleFeignClient;
import com.emazon.transactions.infrastructure.output.feign.dto.UpdateQuantityFeignDto;
import com.emazon.transactions.infrastructure.output.feign.mapper.UpdateQuantityFeignMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class ArticleFeignAdapter implements IArticlePersistencePort {

    private final ArticleFeignClient articleFeignClient;
    private final UpdateQuantityFeignMapper updateQuantityFeignMapper;

    @Override
    public void updateArticleQuantity(ArticleQuantity articleQuantity) {
        UpdateQuantityFeignDto updateQuantityFeignDto = updateQuantityFeignMapper.toFeignDto(articleQuantity);
        articleFeignClient.updateArticleQuantity(updateQuantityFeignDto);
    }

    @Override
    public Article getArticleById(Long articleId) {
        ResponseEntity<Article> article = articleFeignClient.getArticleById(articleId);
        return article.getBody();
    }
}
