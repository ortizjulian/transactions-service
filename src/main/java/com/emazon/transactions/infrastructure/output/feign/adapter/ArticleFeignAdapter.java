package com.emazon.transactions.infrastructure.output.feign.adapter;

import com.emazon.transactions.domain.model.UpdateQuantity;
import com.emazon.transactions.domain.spi.IArticlePersistencePort;
import com.emazon.transactions.infrastructure.output.feign.client.ArticleFeignClient;
import com.emazon.transactions.infrastructure.output.feign.dto.UpdateQuantityFeignDto;
import com.emazon.transactions.infrastructure.output.feign.mapper.UpdateQuantityFeignMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleFeignAdapter implements IArticlePersistencePort {

    private final ArticleFeignClient articleFeignClient;
    private final UpdateQuantityFeignMapper updateQuantityFeignMapper;

    @Override
    public void updateArticleQuantity(UpdateQuantity updateQuantity) {
        UpdateQuantityFeignDto updateQuantityFeignDto = updateQuantityFeignMapper.toFeignDto(updateQuantity);
        articleFeignClient.updateArticleQuantity(updateQuantityFeignDto);
    }
}
