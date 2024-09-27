package com.emazon.transactions.domain.api;

import com.emazon.transactions.domain.model.ArticleQuantity;

import java.util.List;

public interface ISaleServicePort {
    void createSale(List<ArticleQuantity> saleList, Long userId);
}
