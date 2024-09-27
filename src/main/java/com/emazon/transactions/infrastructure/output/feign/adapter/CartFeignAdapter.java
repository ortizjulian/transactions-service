package com.emazon.transactions.infrastructure.output.feign.adapter;

import com.emazon.transactions.domain.spi.ICartPersistencePort;
import com.emazon.transactions.infrastructure.output.feign.client.CartFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartFeignAdapter implements ICartPersistencePort {
    private final CartFeignClient cartFeignClient;

    @Override
    public void removeArticleFromCart(Long articleId) {
        cartFeignClient.removeArticleFromCart(articleId);
    }
}
