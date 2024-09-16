package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.UpdateQuantity;

public interface IArticlePersistencePort {
    void updateArticleQuantity(UpdateQuantity updateQuantity);
}
