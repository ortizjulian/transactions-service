package com.emazon.transactions.domain.spi;

import com.emazon.transactions.domain.model.StatusCodeEnum;
import com.emazon.transactions.domain.model.UpdateQuantity;

public interface IArticlePersistencePort {
    StatusCodeEnum updateArticleQuantity(UpdateQuantity updateQuantity);
}
