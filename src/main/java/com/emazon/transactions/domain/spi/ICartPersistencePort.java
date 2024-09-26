package com.emazon.transactions.domain.spi;

public interface ICartPersistencePort {
    void removeArticleFromCart(Long articleId);
}
